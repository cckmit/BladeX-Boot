
package org.springblade.modules.EnterpriseResource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import lombok.SneakyThrows;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.constant.OssConstant;
import org.springblade.common.utils.DownloadFile;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.oss.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.FileVO;
import org.springblade.modules.EnterpriseResource.vo.demo;
import org.springblade.modules.EnterpriseResource.wrapper.FileWrapper;
import org.springblade.modules.system.mapper.DeptMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 企业资产附件表 控制器
 *
 * @author BladeX
 * @since 2021-09-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-resource/file")
@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
@Api(value = "企业资产附件表", tags = "企业资产附件表接口")
public class FileController extends BladeController {

	private final IFileService fileService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入file")
	public R<FileVO> detail(AllFile file) {
		AllFile detail = fileService.getOne(Condition.getQueryWrapper(file));
		return R.data(FileWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 企业资产附件表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入file")
	public R<IPage<FileVO>> list(AllFile file, Query query) {
		IPage<AllFile> pages = fileService.page(Condition.getPage(query), Condition.getQueryWrapper(file));
		return R.data(FileWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 企业资产附件表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入file")
	public R<IPage<FileVO>> page(FileVO file, Query query) {
		IPage<FileVO> pages = fileService.selectFilePage(Condition.getPage(query), file);
		return R.data(pages);
	}

	/**
	 * 新增 企业资产附件表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入file")
	public R save(@Valid @RequestBody AllFile file) {
		return R.status(fileService.save(file));
	}

	/**
	 * 修改 企业资产附件表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入file")
	public R update(@Valid @RequestBody AllFile file) {
		return R.status(fileService.updateById(file));
	}

	/**
	 * 新增或修改 企业资产附件表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入file")
	public R submit(@Valid @RequestBody AllFile file) {
		return R.status(fileService.saveOrUpdate(file));
	}


	/**
	 * 删除 企业资产附件表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(fileService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 下载文件到本地
	 */
	@PostMapping("/DownloadFileAll")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "下载文件到本地", notes = "传入list")
	public void DownloadFileAll(@RequestBody List<AllFile> file) throws IOException {
		if (file.isEmpty()){
			DownloadFile.saveUrlAs(file);
		}

	}


/**********************************业务上传OSS************************************************************************/
	/**
	 * 构建附件表
	 *
	 * @param fileName  文件名
	 * @param fileSize  文件大小
	 * @param bladeFile 对象存储文件
	 * @return attachId
	 */
//	private Long buildAttach(String fileName, Long fileSize, BladeFile bladeFile) {
//		String fileExtension = FileUtil.getFileExtension(fileName);
//		AllFile allFile = new AllFile();
//		allFile.setDomain(bladeFile.getDomain());
//		allFile.setFileAddess(bladeFile.getLink());
//		allFile.setFileName(bladeFile.getName());
//		allFile.setOriginalName(bladeFile.getOriginalName());
//		allFile.setFileSize(fileSize);
//		allFile.setExtension(fileExtension);
//		fileService.save(allFile);
//		return allFile.getId();
//	}
	//================================================纯单文件上传===========================================================================================
	private MinioClient client;
	private MinioTemplate minioTemplate;
	private DeptMapper deptMapper;
	@SneakyThrows
	@PostMapping("/put-DownloadFileAll")
	public R<BladeFile> put(@RequestParam MultipartFile file) {
		String filename = fileName(file.getOriginalFilename());
		client.putObject((PutObjectArgs) ((io.minio.PutObjectArgs.Builder) ((io.minio.PutObjectArgs.Builder) PutObjectArgs.builder().bucket(OssConstant.MINIO_Bucket)).object(filename)).stream(file.getInputStream(), (long) file.getSize(), -1L).contentType("application/octet-stream").build());
		BladeFile files = new BladeFile();
		files.setOriginalName(file.getOriginalFilename());
		files.setName(filename);
		files.setDomain(this.getOssHost());
		files.setLink(this.fileLinkr(filename));
		return R.data(files);
	}

	public String getOssHost(){
		return OssConstant.MINIO_address + OssConstant.MINIO_Ossendpoint + OssConstant.MINIO_Bucket;
	}

	public String fileLinkr(String filename){
		return this.getOssHost() +"/"+ filename;
	}


	public String fileName(String originalFilename) {
		BladeUser User = AuthUtil.getUser();
		//获取需要进行匹对判断冲突的列表
		String Com = User.getAccount().equals("admin")?"admin":deptMapper.selectById(User.getDetail().getStr(CommonConstant.PROF_COM_ID)).getDeptName();
		return "upload/business/"+ Com + "/" + DateUtil.today() + "/" + StringUtil.randomUUID() + "." + FileUtil.getFileExtension(originalFilename);
	}








}

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
import org.apache.commons.fileupload.FileItem;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.constant.OssConstant;
import org.springblade.common.utils.DownloadFile;
import org.springblade.common.utils.UploadZip;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
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
import org.springblade.modules.EnterpriseResource.excel.AptitudeExcel;
import org.springblade.modules.EnterpriseResource.excel.AptitudeImporter;
import org.springblade.modules.EnterpriseResource.excel.UploadFile;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.EnterpriseResource.vo.FileVO;
import org.springblade.modules.EnterpriseResource.wrapper.FileWrapper;
import org.springblade.modules.system.mapper.DeptMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

	private final IAptitudeService aptitudeService;
	/*
		zip全局地址 和返回的地址
	*/

	private static   String pathName = "D:\\yaoshi";
	private static   String dec = "D:\\yaoshi\\qq";


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
	 *
	 * 删除 企业资产附件表
	 *
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


	//================================================纯单文件上传===========================================================================================
	private MinioClient client;
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


	/**
	 *
	 *上传zip解压到文件夹
	 *
	 *
	 */
	@PostMapping("/parseAndAdd")
	@ApiOperationSupport(order = 18)
	@ApiOperation(value = "上传zip到本地", notes = "传入zipFile")
	public R parseAndAdd(@RequestParam MultipartFile file) {

		File temp = new File(pathName);
		//如果文件夹不存在  创建文件夹
		if (!temp.exists()) {
			temp.mkdir();
		}
		//获取文件名（包括后缀）
		String pname = file.getOriginalFilename();
		pathName = pathName + UUID.randomUUID().toString().replaceAll("-", "") + "-" + pname;
		try {
			File dest = new File(pathName);
			file.transferTo(dest);
			// 获取解压出来的文件名 不带后缀
			List<String> fileNames = UploadZip.unZip(dest, dec);

			//拿解压文件夹的名称
			String b =  fileNames.get(0);
			//拼接文件名称
			File f =  new File(dec+"\\"+b);
			//遍历文件夹的文件
			File [] fs =f.listFiles();
			for (File file2:fs){
				if (file2.getName().endsWith(".xlsx")){
					//拿到当前文件二级文件夹
					String c = file2.getName();
					//拼接文件路径
					String a =dec+"\\"+b+c;
					//拼接图片地址路径
					String imgName =dec+"\\"+b+"img"+"\\";
					//通过路径拿 MultipartFile文件流
					FileItem fileItem = UploadFile.createFileItem(a);
					MultipartFile mfile = new CommonsMultipartFile(fileItem);
					Integer	isCovered=1;
					AptitudeImporter aptitudeImporter = new AptitudeImporter(aptitudeService, isCovered == 1,imgName);
					ExcelUtil.save(mfile, aptitudeImporter, AptitudeExcel.class);
				}
			}
			//解析完成   删除本次解析中生成的文件  删除此目录下的所有文件
			UploadZip.deleteFile(dec);
		} catch (Exception e) {
			e.printStackTrace();
			return R.success("操作失败,未知错误");
		}
		return R.success("上传成功" );
	}





}

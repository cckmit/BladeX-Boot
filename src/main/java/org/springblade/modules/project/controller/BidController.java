
package org.springblade.modules.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.project.dto.*;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Bidcom;
import org.springblade.modules.project.service.IBidService;
import org.springblade.modules.project.service.IBidbondService;
import org.springblade.modules.project.service.IBidundertakeService;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.vo.BidbondVO;
import org.springblade.modules.project.vo.BidcomVO;
import org.springblade.modules.project.wrapper.BidWrapper;
import org.springblade.modules.project.wrapper.BidWrapperCopy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2021-07-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/bid")
@Api(value = "投标管理模块", tags = "投标管理模块")
public class BidController extends BladeController {

	private final IBidService bidService;
	private final IBusinessService businessService;
	private final IBidbondService bidbondService;
	private final IBidundertakeService bidundertakeService;
	//region 基础接口
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 101)
	@ApiOperation(value = "发起投标详情", notes = "传入bid")
	public R<BidFormDTO> detail(String bidId) {
		BidFormDTO detail = bidService.getDetail(bidId);
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/lists")
	@ApiOperationSupport(order = 102)
	@ApiOperation(value = "投标列表", notes = "传入bid")
	public R<IPage<BidVO>> list(Bid bid, Query query) {
		IPage<Bid> pages = bidService.page(Condition.getPage(query), Condition.getQueryWrapper(bid));
		return R.data(BidWrapper.build().pageVO(pages));
	}

	//region 废弃
	/**
	 * 自定义分页
	 */
//	@GetMapping("/page")
//	@ApiOperationSupport(order = 3)
//	@ApiOperation(value = "分页", notes = "传入bid")
//	public R<IPage<BidVO>> page(BidVO bid, Query query) {
//		IPage<BidVO> pages = bidService.selectBidPage(Condition.getPage(query), bid);
//		return R.data(pages);
//	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 125)
	@ApiOperation(value = "投标信息保存", notes = "传入bid")
	public R save(@Valid @RequestBody Bid bid) {
		return R.status(bidService.save(bid));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 126)
	@ApiOperation(value = "投标信息更新", notes = "传入bid")
	public R update(@Valid @RequestBody Bid bid) {
		return R.status(bidService.updateById(bid));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 127)
	@ApiOperation(value = "投标信息保存", notes = "传入bid")
	public R submit(@Valid @RequestBody BidFormDTO bidFormDTO) {
		bidService.startBidProcess(bidFormDTO);
		return R.status(true);
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 128)
	@ApiOperation(value = "投标信息删除", notes = "传入bid")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(bidService.removeByIds(Func.toLongList(ids)));
	}


//	/**
//	 * 终止投标
//	 * @return
//	 */
//	@PostMapping("/stop")
//	public R stopBid(@Valid @RequestBody BidToVoidDTO cancelDTO)
//	{
//		return R.status(bidService.stopBid(cancelDTO));
//	}
	//endregion
	//endregion
	//region 流程接口
	/**
	 * 列表分页
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "recordName", value = "商机名称", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectName", value = "投标名称", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "biddingType", value = "招标方式", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectCatrgory", value = "商机分类", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "expandMode", value = "拓展模式", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "industry", value = "行业", paramType = "query", dataType = "String"),
	})
	@GetMapping("/list")
	@ApiOperationSupport(order = 103)
	@ApiOperation(value = "投标分页", notes = "传入bid")
	public R<IPage<BidListDTO>> page(BidListDTO bid, Query query) {
		IPage<BidListDTO> pages = bidService.selectBidList(Condition.getPage(query), bid);
		return R.data(pages);
	}
	/**
	 * 推送到投标
	 * @return
	 */
	@PostMapping("/pushToBid")
	@ApiOperationSupport(order = 104)
	@ApiOperation(value = "推送至投标管理", notes = "传入businessId")
	public R pushToBid(long businessId)
	{
		return R.status(bidService.pushToBid(businessId));
	}

	@PostMapping("/start-bidprocess")
	@ApiOperationSupport(order = 105)
	@ApiOperation(value = "开启投标流程", notes = "传入流程信息")
	public R startbidProcess(@RequestBody BidFormDTO bidFormDTO) {
		return R.status(bidService.startBidProcess(bidFormDTO));
	}
	/**
	 * 流程详情
	 */
	@GetMapping("/flow-biddetail")
	@ApiOperationSupport(order = 106)
	@ApiOperation(value = "投标流程详情", notes = "传入bid")
	public R<BidDTO> biddetail(String bidId) {
		BidDTO detail = bidService.getBidDetail(bidId);
		return R.data(detail);
	}
	/**
	 * 投标流程审核环节
	 *
	 * @param
	 */
	@PostMapping("/complete-bidtask")
	@ApiOperationSupport(order = 107)
	@ApiOperation(value = "审核投标流程", notes = "传入流程信息")
	public R bidHandle(@RequestBody BidDTO bidDTO){
		return R.status(bidService.completeBidTask(bidDTO));
	}

	/**
	 * 开启投标作废流程
	 *
	 * @param bidId,reason
	 */
	@PostMapping("/start-cancelprocess")
	@ApiOperationSupport(order = 108)
	@ApiOperation(value = "开启投标报废流程", notes = "传入流程信息")
	public R startcancelProcess(Long bidId,String reason) {
		return R.status(bidService.startBidcancelProcess(bidId,reason));
	}

	/**
	 * 投标作废流程审核环节
	 *
	 * @param
	 */
	@PostMapping("/complete-canceltask")
	@ApiOperationSupport(order = 109)
	@ApiOperation(value = "审核投标报废流程", notes = "传入流程信息")
	public R cancelHandle(@RequestBody BidCancelDTO bidCancelDTO){
		return R.status(bidService.completeCancelTask(bidCancelDTO));
	}

	/**
	 * 开启保证金流程
	 *
	 * @param BidbondDTO
	 */
	@PostMapping("/start-bondprocess")
	@ApiOperationSupport(order = 110)
	@ApiOperation(value = "开启保证金流程", notes = "传入流程信息")
	public R startbondProcess(@RequestBody BidbondDTO BidbondDTO) {
		return R.status(bidService.startbondProcess(BidbondDTO));
	}
	/**
	 * 保证金流程审核环节
	 *
	 * @param
	 */
	@PostMapping("/complete-bondtask")
	@ApiOperationSupport(order = 111)
	@ApiOperation(value = "审核保证金流程", notes = "传入流程信息")
	public R bondHandle(@RequestBody BidbondDTO bidbondDTO){
		return R.status(bidService.completeBondTask(bidbondDTO));
	}

	/**
	 * 开启承接流程
	 *
	 * @param bidundertakeFormDTO
	 */
	@PostMapping("/start-undertakeprocess")
	@ApiOperationSupport(order = 112)
	@ApiOperation(value = "开启承接流程", notes = "传入流程信息")
	public R startundertakeProcess(@RequestBody BidundertakeFormDTO bidundertakeFormDTO) {
		return R.status(bidService.startundertakeProcess(bidundertakeFormDTO));
	}
	/**
	 * 承接流程审核环节
	 *
	 * @param bidundertakeDTO
	 */
	@PostMapping("/complete-undertaketask")
	@ApiOperationSupport(order = 113)
	@ApiOperation(value = "审核承接流程", notes = "传入流程信息")
	public R undertakeHandle(@RequestBody BidundertakeDTO bidundertakeDTO){
		return R.status(bidService.completeUndertakeTask(bidundertakeDTO));
	}
	/**
	 * 承接流程审核环节
	 *
	 * @param bidId
	 */
	@PostMapping("/flow-undertakedetail")
	@ApiOperationSupport(order = 114)
	@ApiOperation(value = "承接流程详情", notes = "传入流程信息")
	public R<BidundertakeFormDTO> undertakeDetail(String bidId){
		return R.data(bidService.undertakeDetail(bidId));
	}
	//region 参标单位
	/**
	 * 参标单位列表
	 *
	 * @param bidId
	 */
	@GetMapping("/BidComList")
	@ApiOperationSupport(order = 115)
	@ApiOperation(value = "参标单位列表", notes = "传入bidid")
	public R<List<BidcomVO>> bidcomList(String bidId){
		return R.data(bidService.bidcomList(bidId));
	}

	@PostMapping("/addBidCom")
	@ApiOperationSupport(order = 117)
	@ApiOperation(value = "添加参标单位", notes = "传入bidcom")
	public R addBidCom(@RequestBody Bidcom bidcom){
		return R.data(bidService.addcom(bidcom));
	}

	@PostMapping("/detBidCom")
	@ApiOperationSupport(order = 118)
	@ApiOperation(value = "删除单位列表", notes = "传入bidcomid")
	public R detBidCom(String bidcomid){
		return R.data(bidService.detBidcom(bidcomid));
	}
	//endregion

	@PostMapping("/start-resultProcess")
	@ApiOperationSupport(order = 119)
	@ApiOperation(value = "启动录入开标结果流程", notes = "传入bidcomid")
	public R startResultProcess(@RequestBody BidresultFormDTO bidresultFormDTO){
		return R.status(bidService.startResultProcess(bidresultFormDTO));
	}
	@PostMapping("/complete-resulttask")
	@ApiOperationSupport(order = 120)
	@ApiOperation(value = "审核启动录入开标结果流程", notes = "传入流程信息")
	public R undertakeHandle(@RequestBody BidresultDTO bidresultDTO){
		return R.status(bidService.completeResultTask(bidresultDTO));
	}

	//endregion

	//region
	/**
	 * 列表分页
	 */
	@GetMapping("/bondlist")
	@ApiOperationSupport(order = 121)
	@ApiOperation(value = "保证金分页", notes = "传入bid")
	public R<IPage<BidbondVO>> Bondpage(BidbondVO bidbond, Query query) {
		IPage<BidbondVO> pages = bidbondService.selectBondList(Condition.getPage(query), bidbond);
		return R.data(pages);
	}
	/**
	 * 退还保证金
	 */
	@GetMapping("/bondrecovery")
	@ApiOperationSupport(order = 122)
	@ApiOperation(value = "回收保证金", notes = "传入bid")
	public boolean BondCovery(String id) {
		return bidService.BondCovery(id);
	}
	@GetMapping("/undertakelist")
	@ApiOperationSupport(order = 123)
	@ApiOperation(value = "新承接分页", notes = "传入bid")
	public R<IPage<BidundertakePageDTO>> Bondpage(BidundertakePageDTO bidundertake, Query query) {
		IPage<BidundertakePageDTO> pages = bidundertakeService.selectUndertakeList(Condition.getPage(query), bidundertake);
		return R.data(pages);
	}



	//endregion

	@PostMapping("/bidallflow")
	@ApiOperationSupport(order = 124)
	@ApiOperation(value = "获取投标全流程id", notes = "传入bidId")
	public R<BidFlowDTO> bidallflow(String bidId) {
		return R.data(bidService.bidallflow(bidId));
	}

/**************************************手机端接口*************************************************************************/

	/**
	 *
	 * 	 手机端列表+各种高级筛选查询
	 *
	 */
	@GetMapping("/BidListVO")
	@ApiOperationSupport(order = 102)
	@ApiOperation(value = "手机端列表+各种高级筛选查询", notes = "传入bid")
	public R<IPage<BidVO>> selectBidListVO(BidVO bid, Query query) {
		IPage<BidVO> pages = bidService.selectBidListVO(Condition.getPage(query), bid);
		return R.data(BidWrapperCopy.build().pageVO(pages));
	}

}

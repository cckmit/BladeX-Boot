
package org.springblade.modules.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.poi.ss.formula.functions.Now;
import org.springblade.common.enums.BidStatusEnum;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.project.dto.*;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Bidbond;
import org.springblade.modules.project.entity.Bidcom;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.excel.BidExcel;
import org.springblade.modules.project.excel.BondExcel;
import org.springblade.modules.project.excel.DeptExcel;
import org.springblade.modules.project.service.IBidService;
import org.springblade.modules.project.service.IBidbondService;
import org.springblade.modules.project.service.IBidundertakeService;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.vo.BidbondVO;
import org.springblade.modules.project.vo.BidcomVO;
import org.springblade.modules.project.wrapper.BidWrapper;
import org.springblade.modules.project.wrapper.BidWrapperCopy;
import org.springblade.modules.system.entity.Dept;
import org.springblade.modules.system.entity.Major;
import org.springblade.modules.system.service.IDeptService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  ?????????
 *
 * @author BladeX
 * @since 2021-07-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-project/bid")
@Api(value = "??????????????????", tags = "??????????????????")
public class BidController extends BladeController {

	private final IBidService bidService;
	private final IBusinessService businessService;
	private final IBidbondService bidbondService;
	private final IBidundertakeService bidundertakeService;
	//region ????????????
	/**
	 * ??????
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 101)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R<BidFormDTO> detail(String bidId) {
		BidFormDTO detail = bidService.getDetail(bidId);
		return R.data(detail);
	}

	/**
	 * ??????
	 */
	@GetMapping("/lists")
	@ApiOperationSupport(order = 102)
	@ApiOperation(value = "????????????", notes = "??????bid")
	public R<IPage<BidVO>> list(Bid bid, Query query) {
		IPage<Bid> pages = bidService.page(Condition.getPage(query), Condition.getQueryWrapper(bid));
		return R.data(BidWrapper.build().pageVO(pages));
	}

	//region ??????
	/**
	 * ???????????????
	 */
//	@GetMapping("/page")
//	@ApiOperationSupport(order = 3)
//	@ApiOperation(value = "??????", notes = "??????bid")
//	public R<IPage<BidVO>> page(BidVO bid, Query query) {
//		IPage<BidVO> pages = bidService.selectBidPage(Condition.getPage(query), bid);
//		return R.data(pages);
//	}

	/**
	 * ??????
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 125)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R save(@Valid @RequestBody Bid bid) {
		return R.status(bidService.save(bid));
	}

	/**
	 * ??????
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 126)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R update(@Valid @RequestBody Bid bid) {
		return R.status(bidService.updateById(bid));
	}

	/**
	 * ???????????????
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 127)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R submit(@Valid @RequestBody BidFormDTO bidFormDTO) {
		bidService.startBidProcess(bidFormDTO);
		return R.status(true);
	}


	/**
	 * ??????
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 128)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R remove(@ApiParam(value = "????????????", required = true) @RequestParam String ids) {
		return R.status(bidService.removeByIds(Func.toLongList(ids)));
	}


//	/**
//	 * ????????????
//	 * @return
//	 */
//	@PostMapping("/stop")
//	public R stopBid(@Valid @RequestBody BidToVoidDTO cancelDTO)
//	{
//		return R.status(bidService.stopBid(cancelDTO));
//	}
	//endregion
	//endregion
	//region ????????????
	/**
	 * ????????????
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "recordName", value = "????????????", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectName", value = "????????????", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "biddingType", value = "????????????", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectCatrgory", value = "????????????", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "expandMode", value = "????????????", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "industry", value = "??????", paramType = "query", dataType = "String"),
	})
	@GetMapping("/list")
	@ApiOperationSupport(order = 103)
	@ApiOperation(value = "????????????", notes = "??????bid")
	public R<IPage<BidListDTO>> page(BidListDTO bid, Query query) {
		IPage<BidListDTO> pages = bidService.selectBidList(Condition.getPage(query), bid);
		return R.data(pages);
	}
	/**
	 * ???????????????
	 * @return
	 */
	@PostMapping("/pushToBid")
	@ApiOperationSupport(order = 104)
	@ApiOperation(value = "?????????????????????", notes = "??????businessId")
	public R pushToBid(long businessId)
	{
		return R.status(bidService.pushToBid(businessId));
	}

	@PostMapping("/start-bidprocess")
	@ApiOperationSupport(order = 105)
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	public R startbidProcess(@RequestBody BidFormDTO bidFormDTO) {
		return R.status(bidService.startBidProcess(bidFormDTO));
	}
	/**
	 * ????????????
	 */
	@GetMapping("/flow-biddetail")
	@ApiOperationSupport(order = 106)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R<BidDTO> biddetail(String bidId) {
		BidDTO detail = bidService.getBidDetail(bidId);
		return R.data(detail);
	}
	/**
	 * ????????????????????????
	 *
	 * @param
	 */
	@PostMapping("/complete-bidtask")
	@ApiOperationSupport(order = 107)
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	public R bidHandle(@RequestBody BidDTO bidDTO){
		return R.status(bidService.completeBidTask(bidDTO));
	}

	/**
	 * ????????????????????????
	 *
	 * @param bidId,reason
	 */
	@PostMapping("/start-cancelprocess")
	@ApiOperationSupport(order = 108)
	@ApiOperation(value = "????????????????????????", notes = "??????????????????")
	public R startcancelProcess(Long bidId,String reason) {
		return R.status(bidService.startBidcancelProcess(bidId,reason));
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param
	 */
	@PostMapping("/complete-canceltask")
	@ApiOperationSupport(order = 109)
	@ApiOperation(value = "????????????????????????", notes = "??????????????????")
	public R cancelHandle(@RequestBody BidCancelDTO bidCancelDTO){
		return R.status(bidService.completeCancelTask(bidCancelDTO));
	}

	/**
	 * ?????????????????????
	 *
	 * @param BidbondDTO
	 */
	@PostMapping("/start-bondprocess")
	@ApiOperationSupport(order = 110)
	@ApiOperation(value = "?????????????????????", notes = "??????????????????")
	public R startbondProcess(@RequestBody BidbondDTO BidbondDTO) {
		return R.status(bidService.startbondProcess(BidbondDTO));
	}
	/**
	 * ???????????????????????????
	 *
	 * @param
	 */
	@PostMapping("/complete-bondtask")
	@ApiOperationSupport(order = 111)
	@ApiOperation(value = "?????????????????????", notes = "??????????????????")
	public R bondHandle(@RequestBody BidbondDTO bidbondDTO){
		return R.status(bidService.completeBondTask(bidbondDTO));
	}

	/**
	 * ??????????????????
	 *
	 * @param bidundertakeFormDTO
	 */
	@PostMapping("/start-undertakeprocess")
	@ApiOperationSupport(order = 112)
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	public R startundertakeProcess(@RequestBody BidundertakeFormDTO bidundertakeFormDTO) {
		return R.status(bidService.startundertakeProcess(bidundertakeFormDTO));
	}
	/**
	 * ????????????????????????
	 *
	 * @param bidundertakeDTO
	 */
	@PostMapping("/complete-undertaketask")
	@ApiOperationSupport(order = 113)
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	public R undertakeHandle(@RequestBody BidundertakeDTO bidundertakeDTO){
		return R.status(bidService.completeUndertakeTask(bidundertakeDTO));
	}
	/**
	 * ????????????????????????
	 *
	 * @param bidId
	 */
	@PostMapping("/flow-undertakedetail")
	@ApiOperationSupport(order = 114)
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	public R<BidundertakeFormDTO> undertakeDetail(String bidId){
		return R.data(bidService.undertakeDetail(bidId));
	}
	//region ????????????
	/**
	 * ??????????????????
	 *
	 * @param bidId
	 */
	@GetMapping("/BidComList")
	@ApiOperationSupport(order = 115)
	@ApiOperation(value = "??????????????????", notes = "??????bidid")
	public R<List<BidcomVO>> bidcomList(String bidId){
		return R.data(bidService.bidcomList(bidId));
	}

	@PostMapping("/addBidCom")
	@ApiOperationSupport(order = 117)
	@ApiOperation(value = "??????????????????", notes = "??????bidcom")
	public R addBidCom(@RequestBody Bidcom bidcom){
		return R.data(bidService.addcom(bidcom));
	}

	@PostMapping("/detBidCom")
	@ApiOperationSupport(order = 118)
	@ApiOperation(value = "??????????????????", notes = "??????bidcomid")
	public R detBidCom(String bidcomid){
		return R.data(bidService.detBidcom(bidcomid));
	}
	//endregion

	@PostMapping("/start-resultProcess")
	@ApiOperationSupport(order = 119)
	@ApiOperation(value = "??????????????????????????????", notes = "??????bidcomid")
	public R startResultProcess(@RequestBody BidresultFormDTO bidresultFormDTO){
		return R.status(bidService.startResultProcess(bidresultFormDTO));
	}
	@PostMapping("/complete-resulttask")
	@ApiOperationSupport(order = 120)
	@ApiOperation(value = "????????????????????????????????????", notes = "??????????????????")
	public R undertakeHandle(@RequestBody BidresultDTO bidresultDTO){
		return R.status(bidService.completeResultTask(bidresultDTO));
	}

	//endregion

	//region
	/**
	 * ????????????
	 */
	@GetMapping("/bondlist")
	@ApiOperationSupport(order = 121)
	@ApiOperation(value = "???????????????", notes = "??????bid")
	public R<IPage<BidbondVO>> Bondpage(BidbondVO bidbond, Query query) {
		IPage<BidbondVO> pages = bidbondService.selectBondList(Condition.getPage(query), bidbond);
		return R.data(pages);
	}
	/**
	 * ???????????????
	 */
	@GetMapping("/bondrecovery")
	@ApiOperationSupport(order = 122)
	@ApiOperation(value = "???????????????", notes = "??????bid")
	public boolean BondCovery(String id) {
		return bidService.BondCovery(id);
	}
	@GetMapping("/undertakelist")
	@ApiOperationSupport(order = 123)
	@ApiOperation(value = "???????????????", notes = "??????bid")
	public R<IPage<BidundertakePageDTO>> Bondpage(BidundertakePageDTO bidundertake, Query query) {
		IPage<BidundertakePageDTO> pages = bidundertakeService.selectUndertakeList(Condition.getPage(query), bidundertake);
		return R.data(pages);
	}



	//endregion

	@PostMapping("/bidallflow")
	@ApiOperationSupport(order = 124)
	@ApiOperation(value = "?????????????????????id", notes = "??????bidId")
	public R<BidFlowDTO> bidallflow(String bidId) {
		return R.data(bidService.bidallflow(bidId));
	}

	@PostMapping("/read-projectid")
	public void projectid(MultipartFile file) {

	}
	//??????????????????
	private final IDeptService deptService ;
	@PostMapping("/read-bid")
	public List readNotice(MultipartFile file) {
		List<BidExcel> list = ExcelUtil.read(file, BidExcel.class);
		List ll =  new ArrayList();
		List llll =  new ArrayList();
		List ee =  new ArrayList();
		for (BidExcel i : list) {
			Business detail = new Business();
			LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();
			String name = i.getProjectRecordName();
			if("????????????B???".equals(name.substring(0, 6))){
				name = name.substring(6);
			}else if("????????????".equals(name.substring(0, 4))){
				name = name.substring(4);
			}else if("???????????????".equals(name.substring(0, 5))){
				name = name.substring(5);
			}
			queryWrapper.eq(Business::getRecordName, name);
			try {
			detail = businessService.getOne(queryWrapper);
			if (Func.isEmpty(detail)) {
				ll.add(i.getProjectRecordName());
			}else{
				Bid bid = new Bid();
				bid.setBondPayMethod(i.getBidOpenRecordId()); //????????????
				bid.setBusinessId(detail.getId());
				bid.setProjectName(name);
				bid.setQuotationMethod(i.getProjectManager());
				if(Func.isNotEmpty(i.getIsFrame())) {
					bid.setIsFrame(i.getIsFrame().equals("???") ? 1 : i.getIsFrame().equals("???") ? 0 : null);
				}
				if(Func.isNotEmpty(i.getBidMoney())) {
					bid.setBidAmount(i.getBidMoney().multiply(BigDecimal.valueOf(10000)).doubleValue());
				}
				bid.setBidAgentName(i.getBidPlatform());
				bid.setPublicWebSite(i.getWinBidWebsite());
				bid.setIsNeedBond(1);
				bid.setCreateTime(i.getCreateDate());
				bid.setIsDelete(false);
				if(i.getBidStatus().equals("4")) {
					bid.setBidStatus(BidStatusEnum.BID_WAIT.getValue());
					bid.setStatus(BidStatusEnum.BID_WAIT.getValue());
				}else if (i.getBidStatus().equals("5")){
					bid.setBidStatus(BidStatusEnum.BID_SUCCESS.getValue());
					bid.setStatus(BidStatusEnum.BID_SUCCESS.getValue());
				}else if (i.getBidStatus().equals("6")){
					bid.setBidStatus(BidStatusEnum.BID_SUCCESS.getValue());
					bid.setStatus(BidStatusEnum.UNDERTAKE_SUCCESS.getValue());
				}
				bid.setCreateDept(Long.valueOf(deptService.getDeptId(i.getCode2()).get(0)));
				bid.setApplyTime(DateUtil.now());
				bidService.save(bid);
			}
			}catch (Exception e){
				queryWrapper.eq(Business::getClientName, i.getProjectManager());
				detail = businessService.getOne(queryWrapper);
				if (Func.isEmpty(detail)) {
					llll.add(i.getProjectRecordName());
				}else{
					Bid bid = new Bid();
					bid.setBondPayMethod(i.getBidOpenRecordId()); //????????????
					bid.setBusinessId(detail.getId());
					bid.setProjectName(name);
					bid.setQuotationMethod(i.getProjectManager());
					if(Func.isNotEmpty(i.getIsFrame())) {
						bid.setIsFrame(i.getIsFrame().equals("???") ? 1 : i.getIsFrame().equals("???") ? 0 : null);
					}
					if(Func.isNotEmpty(i.getBidMoney())) {
						bid.setBidAmount(i.getBidMoney().multiply(BigDecimal.valueOf(10000)).doubleValue());
					}
					bid.setBidAgentName(i.getBidPlatform());
					bid.setPublicWebSite(i.getWinBidWebsite());
					bid.setIsNeedBond(1);
					bid.setCreateTime(i.getCreateDate());
					bid.setIsDelete(false);
					if(i.getBidStatus().equals("4")) {
						bid.setBidStatus(BidStatusEnum.BID_WAIT.getValue());
						bid.setStatus(BidStatusEnum.BID_WAIT.getValue());
					}else if (i.getBidStatus().equals("5")){
						bid.setBidStatus(BidStatusEnum.BID_SUCCESS.getValue());
						bid.setStatus(BidStatusEnum.BID_SUCCESS.getValue());
					}else if (i.getBidStatus().equals("6")){
						bid.setBidStatus(BidStatusEnum.BID_SUCCESS.getValue());
						bid.setStatus(BidStatusEnum.UNDERTAKE_SUCCESS.getValue());
					}
					bid.setCreateDept(Long.valueOf(deptService.getDeptId(i.getCode2()).get(0)));
					bid.setApplyTime(DateUtil.now());
					bidService.save(bid);
				}
			}
		}
		System.out.println("===============404==============");
		System.out.println(ll);
		System.out.println("==============40404=============");
		System.out.println(llll);
		System.out.println("==============error=============");
		System.out.println(ee);
		return ll;
	}
	@PostMapping("/read-bond")
	public List readBond(MultipartFile file){
		List<BondExcel> list = ExcelUtil.read(file, BondExcel.class);
		for (BondExcel i : list) {
			Bidbond bond = new Bidbond();
			LambdaQueryWrapper<Bid> queryWrapper = new LambdaQueryWrapper<>();
			queryWrapper.eq(Bid::getBondPayMethod, i.getRecordId());
			Bid bid = bidService.getOne(queryWrapper);
			if(Func.isNotEmpty(bid)) {
				bond.setId(bid.getId());
				bond.setBidId(i.getBondId());
				bond.setBondAmount(i.getMarginAmount().multiply(BigDecimal.valueOf(1)).doubleValue());
				bond.setBondPayMethod(Func.isEmpty(i.getPaymentForm())?"ZZ":null);
				bond.setBondRecoveryTime(i.getPartnersPaymentTime());
				Integer status = null;
				switch (i.getBondState()){
					case "-1":
						status = BidStatusEnum.BOND_REJECT.getValue();
						break;
					case "0":
						status = BidStatusEnum.BOND_Z_WAIT.getValue();
						break;
					case "1":
						status = BidStatusEnum.BOND_Z_SUCCESS.getValue();
						break;
					case "2":
						status = BidStatusEnum.BOND_APPROPRIAT.getValue();
						break;
					case "5":
						status = BidStatusEnum.IS_BOND_SUCCESS.getValue();
						break;
					default :
						break;
				}
				bond.setBondStatus(status);
				bond.setApplyTime(DateUtil.now());
				bidbondService.save(bond);
			}
		}
		return list;
	}
/**************************************???????????????*********************************************************************************************/

	/**
	 *
	 * 	 ???????????????+????????????????????????
	 *
	 */
	@GetMapping("/BidListVO")
	@ApiOperationSupport(order = 102)
	@ApiOperation(value = "???????????????+????????????????????????", notes = "??????bid")
	public R<IPage<BidVO>> selectBidListVO(BidVO bid, Query query) {
		IPage<BidVO> pages = bidService.selectBidListVO(Condition.getPage(query), bid);
		return R.data(BidWrapperCopy.build().pageVO(pages));
	}
	/**
	 * ??????
	 */
	@GetMapping("/mobile_detail")
	@ApiOperationSupport(order = 101)
	@ApiOperation(value = "??????????????????", notes = "??????bid")
	public R<BidFormDTO> mobileDetail(String bidId) {
		BidFormDTO detail = bidService.mobileDetail(bidId);
		return R.data(detail);
	}

	/**
	 * ?????????????????????????????????
	 *
	 * @param bidId
	 */
	@PostMapping("/mobilUndertakeDetail")
	@ApiOperationSupport(order = 114)
	@ApiOperation(value = "???????????????????????????", notes = "??????????????????")
	public R<BidundertakeFormDTO> mobilUndertakeDetail(String bidId){
		return R.data(bidService.undertakeDetail(bidId));
	}


}

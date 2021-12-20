package org.springblade.modules.EnterpriseResource.excel;

import lombok.RequiredArgsConstructor;
import org.springblade.core.excel.support.ExcelImporter;
import org.springblade.modules.EnterpriseResource.service.IAptitudeService;

import java.util.List;


@RequiredArgsConstructor
public class AptitudeImporter implements ExcelImporter<AptitudeExcel> {

	private final IAptitudeService aptitudeService;

	private final Boolean isCovered;

	private final String imgName;


	@Override
	public void save(List<AptitudeExcel> data) {
		aptitudeService.importAptitude(data,isCovered,imgName);
	}
}

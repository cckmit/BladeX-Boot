package org.springblade.modules.EnterpriseResource.excel;

import lombok.RequiredArgsConstructor;
import org.springblade.core.excel.support.ExcelImporter;

import java.util.List;


@RequiredArgsConstructor
public class AptitudeImporter implements ExcelImporter<AptitudeExcel> {

	private final Boolean isCovered;
	@Override
	public void save(List<AptitudeExcel> data) {

	}
}

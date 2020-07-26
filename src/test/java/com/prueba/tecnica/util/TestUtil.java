package com.prueba.tecnica.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.prueba.tecnica.dto.SaleDetailDto;
import com.prueba.tecnica.dto.SaleDto;

public class TestUtil {

	public static SaleDto getSaleDto() {
		SaleDto dto = new SaleDto();
		dto.setDate(new Date());
		dto.setTableId(1l);
		dto.setTotalAmount(10000);

		List<SaleDetailDto> detailList = new ArrayList<>();
		SaleDetailDto detail = new SaleDetailDto();
		detail.setAmount(10000);
		detail.setProductId(1l);
		detail.setQuantity(2);
		detailList.add(detail);
		dto.setSaleDetail(detailList);
		return dto;
	}
}

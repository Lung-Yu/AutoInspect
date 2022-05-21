package com.tygr.inspectors;

import com.tygr.inspectors.service.ExecutorService;
import com.tygr.inspectors.service.XASTService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InspectorsApplicationTests {

	@Autowired
	private XASTService xastService;

	@Autowired
	private ExecutorService executorService;

	@Test
	void contextLoads() {
	}

	@Test
	void xastScript() {
		String cmdScript = xastService.xast("C:\\Users\\lungyu\\Documents\\GitHub\\NYUST_MIPL_ImageToolBox",
				"C:\\Users\\lungyu\\Desktop\\VSG\\VS2019_TEST");
		
	}
}

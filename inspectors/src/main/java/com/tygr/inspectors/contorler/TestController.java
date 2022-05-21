package com.tygr.inspectors.contorler;

import com.tygr.inspectors.service.ExecutorService;
import com.tygr.inspectors.service.XASTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private XASTService xastService;

    @Autowired
    private ExecutorService executorService;

    @GetMapping("test/")
    public String test() {

        String cmdScript = xastService.xast("C:\\Users\\lungyu\\Documents\\GitHub\\NYUST_MIPL_ImageToolBox",
                "C:\\Users\\lungyu\\Desktop\\VSG\\VS2019_TEST");
        System.out.println(cmdScript);
        return  executorService.execute(cmdScript);
    }
}

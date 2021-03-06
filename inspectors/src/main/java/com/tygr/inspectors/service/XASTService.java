package com.tygr.inspectors.service;

import com.tygr.inspectors.modules.XAST.VSG.InspectVSG;
import com.tygr.inspectors.modules.XAST.VSG.LanguageVSG;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public final class XASTService {
    @Value("${path.executor.vsg:C:\\Program Files (x86)\\VisualCodeGrepper\\VisualCodeGrepper.exe}")
    private String path_exector_vsg;

    public String xast(String folderOfInspectTarget, String folderOfReport) {
        InspectVSG inspector = new InspectVSG(path_exector_vsg);
        inspector.setLanguage(LanguageVSG.CS);
        // inspector.setReportType(ReportTypeVSG.CSV);
        inspector.setReportType("csv");
        String cmdScript = inspector.inspect(folderOfInspectTarget, folderOfReport);
        return cmdScript;
    }
}

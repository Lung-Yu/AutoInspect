package com.tygr.inspectors.modules.XAST.VSG;

import com.tygr.inspectors.modules.XAST.Inspect;

public class InspectVSG implements Inspect {

    private final String path_exector;

    private LanguageVSG language;

    private ReportTypeVSG reportType;

    public InspectVSG(String exectorPath) {
        this.path_exector = pathFilter(exectorPath);
    }

    protected String pathFilter(String str){
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        return sb.toString();
    }

    @Override
    public String inspect(String target, String result) {
        StringBuilder cmdScriptBuilder = new StringBuilder();
        
        //input filter process
        final String path_target = pathFilter(target);
        final String path_report = pathFilter(result + this.reportType.getFileExtension());

        //executor
        cmdScriptBuilder.append(this.path_exector);
        cmdScriptBuilder.append(" ");
        
        //base script argv
        cmdScriptBuilder.append("-c -v ");

        //target language
        cmdScriptBuilder.append("-l ");
        cmdScriptBuilder.append(this.language.getArgument());
        cmdScriptBuilder.append(" ");

        //target file or folder
        cmdScriptBuilder.append("-t ");
        cmdScriptBuilder.append(path_target);
        cmdScriptBuilder.append(" ");

        //report type
        cmdScriptBuilder.append(reportType.getKey());
        cmdScriptBuilder.append(" ");
        cmdScriptBuilder.append(path_report);
        
        return cmdScriptBuilder.toString();
    }

    public void setLanguage(LanguageVSG language){
        this.language = language;
    }

    public void setReportType(ReportTypeVSG reportType){
        this.reportType = reportType;
    }
}


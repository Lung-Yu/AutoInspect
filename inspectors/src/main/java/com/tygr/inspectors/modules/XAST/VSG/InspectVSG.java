package com.tygr.inspectors.modules.XAST.VSG;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.tygr.inspectors.annotations.Language;
import com.tygr.inspectors.annotations.Languages;
import com.tygr.inspectors.annotations.ReportType;
import com.tygr.inspectors.annotations.ReportTypes;
import com.tygr.inspectors.modules.XAST.Inspect;
import com.tygr.inspectors.modules.XAST.InspectReport;
import com.tygr.inspectors.modules.XAST.InspectSAST;

@Language(value = "CPP", name = "CPP")
@Language(value = "PLSQL", name = "PLSQL")
@Language(value = "JAVA", name = "JAVA")
@Language(value = "CS", name = "CS")
@Language(value = "VB", name = "VB")
@Language(value = "COBOL", name = "COBOL")
@ReportType(value = "csv", args = "--csv-export", extension = ".csv")
@ReportType(value = "txt", args = "--results", extension = ".txt")
@ReportType(value = "xml", args = "--export", extension = ".xml")
public class InspectVSG implements Inspect, InspectSAST, InspectReport {

    private final String path_exector;

    private LanguageVSG language;

    // private ReportTypeVSG reportType;
    private ReportType reportType;

    public InspectVSG(String exectorPath) {
        this.path_exector = pathFilter(exectorPath);
    }

    protected String pathFilter(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        return sb.toString();
    }

    @Override
    public String inspect(String target, String result) {
        StringBuilder cmdScriptBuilder = new StringBuilder();

        // input filter process
        final String path_target = pathFilter(target);
        final String path_report = pathFilter(result + this.reportType.extension());

        // executor
        cmdScriptBuilder.append(this.path_exector);
        cmdScriptBuilder.append(" ");

        // base script argv
        cmdScriptBuilder.append("-c -v ");

        // target language
        cmdScriptBuilder.append("-l ");
        cmdScriptBuilder.append(this.language.getArgument());
        cmdScriptBuilder.append(" ");

        // target file or folder
        cmdScriptBuilder.append("-t ");
        cmdScriptBuilder.append(path_target);
        cmdScriptBuilder.append(" ");

        // report type
        cmdScriptBuilder.append(reportType.args());
        cmdScriptBuilder.append(" ");
        cmdScriptBuilder.append(path_report);

        return cmdScriptBuilder.toString();
    }

    public void setLanguage(LanguageVSG language) {
        this.language = language;
    }

    public boolean setReportType(String reportType) {
        for (ReportType type : getReportTypes()) {
            if (type.value().equals(reportType)) {
                this.reportType = type;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSupportLanguage(String args) {
        List<String> ls = getSupportLangnuages();

        if (ls.contains(args)) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> getSupportLangnuages() {
        Annotation[] annotations = this.getClass().getAnnotations();

        List<String> supports = new ArrayList<String>();

        for (Annotation annotation : annotations) {
            if (annotation instanceof Languages) {
                Languages ls = (Languages) annotation;
                for (com.tygr.inspectors.annotations.Language item : ls.value()) {
                    supports.add(item.name());
                }
            }
        }

        return supports;
    }

    @Override
    public List<ReportType> getReportTypes() {

        Annotation[] annotations = this.getClass().getAnnotations();

        List<ReportType> supports = new ArrayList<ReportType>();

        for (Annotation annotation : annotations) {
            if (annotation instanceof ReportTypes) {
                ReportTypes ls = (ReportTypes) annotation;
                for (ReportType item : ls.value()) {
                    supports.add(item);
                }
            }
        }

        return supports;
    }
}

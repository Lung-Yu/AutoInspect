package com.tygr.inspectors.service.impl;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.tygr.inspectors.annotations.Languages;
import com.tygr.inspectors.modules.XAST.VSG.InspectVSG;

import org.springframework.stereotype.Service;

@Service
public class SASTServiceReflectionImpl {

    public List<String> getSupportLangnuages(String toolName) {
        if (toolName.equalsIgnoreCase("VSG")) {
            return getSupportLangnuages(InspectVSG.class);
        }
        return null;
    }

    protected List<String> getSupportLangnuages(Class<?> target) {
        Annotation[] annotations = target.getAnnotations();

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
}

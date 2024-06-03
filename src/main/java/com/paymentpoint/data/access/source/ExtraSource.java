package com.paymentpoint.data.access.source;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.entities.Extra;
import com.paymentpoint.data.utils.ObjectUtils;

@SuppressWarnings("unchecked")
public class ExtraSource extends AbstractSource<Extra> {

    private static final List<Extra> REPOSITORY;
    private static final String URL_FILE = "src/main/resources/source/extras.ppj";

    static {
        Object data = ObjectUtils.inportObject(URL_FILE);
        if (data != null)
            REPOSITORY = (List<Extra>) data;
        else
            REPOSITORY = new ArrayList<>();
    }

    @Override
    protected IntFunction<Extra[]> getClassData() {
        return Extra[]::new;
    }

    @Override
    protected List<Extra> data() {
        return REPOSITORY;
    }

    @Override
    protected void processChanges() {
        ObjectUtils.exportObject(REPOSITORY, URL_FILE);
        Observer.update(Data.EXTRA);
    }

    @Override
    protected void processRemoveRegister(Extra register) {
    }

    @Override
    protected void processInsertRegister(Extra register) {
    }

    @Override
    protected void processModifyRegister(Extra oldRegister, Extra newRegister) {
    }
}

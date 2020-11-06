package com.linmsen.db.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.linmsen.db.properties.MybatisPlusAutoFillProperties;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class DateObjectHandler implements MetaObjectHandler {
    private MybatisPlusAutoFillProperties autoFillProperties;

    public DateObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(autoFillProperties.getCreateTimeField(),metaObject);
        Object updateTime = getFieldValByName(autoFillProperties.getCreateTimeField(),metaObject);
        if (createTime == null || updateTime == null) {
            Date date = new Date();
            if (createTime == null) {
                setFieldValByName(autoFillProperties.getCreateTimeField(), date, metaObject);
            }
            if (updateTime == null) {
                setFieldValByName(autoFillProperties.getUpdateTimeField(), date, metaObject);
            }
        }
    }

    public void updateFill(MetaObject metaObject) {
        setFieldValByName(autoFillProperties.getUpdateTimeField(), new Date(), metaObject);
    }
}

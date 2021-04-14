package com.zn.study.loader;
public  class LocalService  implements IService {
    @Override
    public String sayHello() {
        return "Hello LocalService";
    }
    @Override
    public String getScheme() {
        return "local";
    }
}

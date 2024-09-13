package com.quanshi.shopping_manager_api.service;

import com.quanshi.shopping_common.result.BaseResult;

public interface ILoginService {

    BaseResult login(String username,String password);

}

import type { AxiosRequestConfig, AxiosRequestHeaders } from 'axios';
import axios from 'axios';
import type { Restful } from './types';

export const createApi = (config: AxiosRequestConfig, headers?: AxiosRequestHeaders) => {
  return async function <T>(requestDetail: AxiosRequestConfig): Promise<Restful<T>> {
    // eslint-disable-next-line no-param-reassign
    headers = { ...headers, token: '' };
    // 手动配置全局headers
    const instance = axios.create({
      ...config,
      headers,
    });

    /**
     * 拦截器
     */
    // instance.interceptors.request.use((params: AxiosRequestConfig) => {
    //   // 请求前处理数据
    //   return params;
    // });

    let res;
    try {
      res = await instance.request({
        ...requestDetail,
      });
      if (res.status === 400) {
        // 请求参数错误
        console.log('400 错误！');
      } else if (res.status === 401) {
        // 未授权
        console.log('401 错误！');
      } else if (res.status === 403) {
        //
        console.log('403 错误！');
      }
    } catch (err) {
      console.log('出错！');
    }

    return res?.data;
  };
};

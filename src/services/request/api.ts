import { createApi } from '.';

const request = createApi({
  baseURL: 'http://localhost:8080/api/',
});

export const getToken = () => {
  return window.localStorage.getItem('token') || '';
};

// id: string, password: string
export async function login_({ ...args }) {
  return await request<{
    id: string;
    token: string;
  }>({
    method: 'POST',
    url: '/login',
    data: {
      ...args,
    },
    headers: {
      token: getToken(),
    },
  });
}

export async function login__(id: string, password: string) {
  return await request<{ code: string; msg: string; data: any }>({
    method: 'POST',
    url: '/login',
    data: {
      id,
      password,
    },
    headers: {
      token: getToken(),
    },
  });
}

export async function currentUser(id: string) {
  return request<API.CurrentUser>({
    method: 'POST',
    url: '/userInfo',
    data: {
      id,
    },
    headers: {
      token: getToken(),
    },
  });
}

export async function verifyToken(token: string) {
  return request<{ id: string }>({
    method: 'POST',
    url: '/token/verify',
    data: {
      token,
    },
    headers: {
      token: getToken(),
    },
  });
}

export async function allUsers() {
  return request<API.CurrentUser[]>({
    method: 'POST',
    url: '/admin/all-users',
    data: {},
    headers: {
      token: getToken(),
    },
  });
}

export async function updateInfo(info: {
  id: string | undefined;
  name: string;
  description: string;
  email: string;
  gender: string | undefined;
}) {
  return request<API.CurrentUser>({
    method: 'POST',
    url: '/admin/update-user-info',
    data: {
      ...info,
    },
    headers: {
      token: getToken(),
    },
  });
}

export async function queryGoods() {
  return request<API.Goods[]>({
    method: 'POST',
    url: '/goods/query',
    data: {},
    headers: {
      token: getToken(),
    },
  });
}

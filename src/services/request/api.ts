import { createApi } from '.';

const request = createApi({
  baseURL: 'http://localhost:8080/api/',
});

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
  });
}

export async function currentUser(id: string) {
  return request<API.CurrentUser>({
    method: 'POST',
    url: '/userInfo',
    data: {
      id,
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
  });
}

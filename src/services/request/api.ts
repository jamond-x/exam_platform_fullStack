import { createApi } from '.';

const request = createApi({
  baseURL: 'http://localhost:8080/api/',
});

console.log(window.localStorage.getItem('token'));

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
      token: window.localStorage.getItem('token') || '',
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
      token: window.localStorage.getItem('token') || '',
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
      token: window.localStorage.getItem('token') || '',
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
      token: window.localStorage.getItem('token') || '',
    },
  });
}

export async function allUsers() {
  return request<{ id: string }>({
    method: 'POST',
    url: '/admin/all-users',
    data: {},
    headers: {
      token: window.localStorage.getItem('token') || '',
    },
  });
}

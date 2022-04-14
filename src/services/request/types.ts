export interface Restful<T> {
  code: string | number;
  msg: string;
  data: T | undefined;
}

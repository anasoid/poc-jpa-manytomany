import dayjs from 'dayjs';
import { IProduct } from 'app/shared/model/product.model';

export interface ICategory {
  id?: number;
  code?: string | null;
  name?: string | null;
  description?: string | null;
  modifiedDate?: dayjs.Dayjs | null;
  createdDate?: dayjs.Dayjs | null;
  products?: IProduct[] | null;
}

export const defaultValue: Readonly<ICategory> = {};

import dayjs from 'dayjs';
import { ICategory } from 'app/shared/model/category.model';

export interface IProduct {
  id?: number;
  code?: string | null;
  name?: string | null;
  description?: string | null;
  modifiedDate?: dayjs.Dayjs | null;
  createdDate?: dayjs.Dayjs | null;
  catgories?: ICategory[] | null;
}

export const defaultValue: Readonly<IProduct> = {};

import { CustomerGroup } from "../customerGroup/customerGroupInterface";

export interface DataTableInput {
  draw: number;
  start: number;
  length: number;
  order: {
    dir: string;
  }[];
}

export interface DataTableOutput {
  draw: number;
  recordsTotal: number;
  recordsFiltered: number;
  data: CustomerGroup[];
  error: string | null;
}

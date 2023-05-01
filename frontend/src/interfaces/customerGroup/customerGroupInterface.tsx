export interface CustomerGroup {
  id: number;
  title: string;
  type: string;
  countCus: number;
  note: string;
  createAt: Date;
  defaultPricingPolicyId: number;
  defaultPaymentMethodId: string;
  defaultDiscountRate: number;
}

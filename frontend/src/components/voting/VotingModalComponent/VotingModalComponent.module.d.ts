declare module "*.module.css" {
  const styles: { [key: string]: string };
  export default styles;
}

declare module "*.js" {
  const value: any;
  export default value;
}

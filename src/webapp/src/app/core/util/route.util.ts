export function genRouterLink(routes: string[]): string {
  const backslash = '/';
  let routerLink: string = backslash;
  routerLink += routes.join(backslash);
  return routerLink;
}

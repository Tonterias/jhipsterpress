# Problem 31: How to enable scrollPositionRestoration


NOTE: JhipsterPress is changing its name to Springular, so be aware of it! I apologize for the inconvinience

This is the problem. Sometimes when you click on a link in the middle of a long page when the next page gets loaded, it places the new page in about the same place of the link of the previous page (not on the top). So to get the top change the scrollPositionRestoration: 'enabled' in the ../src/main/webapp/app/app-routing.module.ts to avoid that problem.

	import { NgModule } from '@angular/core';
	import { RouterModule } from '@angular/router';
	import { errorRoute, navbarRoute } from './layouts';
	import { DEBUG_INFO_ENABLED } from 'app/app.constants';
	
	const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];
	
	@NgModule({
	    imports: [
	        RouterModule.forRoot(
	            [
	                ...LAYOUT_ROUTES,
	                {
	                    path: 'admin',
	                    loadChildren: './admin/admin.module#JhipsterpressAdminModule'
	                }
	            ],
	            { useHash: true, enableTracing: DEBUG_INFO_ENABLED, scrollPositionRestoration: 'enabled',
	                anchorScrolling: 'enabled' }
	        )
	    ],
	    exports: [RouterModule]
	})
	export class JhipsterpressAppRoutingModule {}
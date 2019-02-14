# Problem 27: How to add static pages to the project:


First, create the component you want to add in the folder you need. I created an ABOUT page in the static folder: /src/main/webapp/app/static/about/about.component.ts


	import { Component, OnInit } from '@angular/core';
	import { Principal } from 'app/core';
	
	@Component({
	    selector: 'jhi-about',
	    templateUrl: './about.component.html'
	})
	export class AboutComponent implements OnInit {
	    currentAccount: any;
	
	    constructor(private principal: Principal) {}
	
	    ngOnInit() {
	        this.principal.identity().then(account => {
	            this.currentAccount = account;
	        });
	    }
	}
	
Then, add the content you want to add to the HTML: /src/main/webapp/app/static/about/about.component.html

Add the component into the /src/main/webapp/app/home/home.route.ts:


	import { Route } from '@angular/router';
	
	import { HomeComponent } from './';
	import { AboutComponent } from 'app/static/about/about.component';
	
	export const HOME_ROUTE: Route = {
	    path: '',
	    component: HomeComponent,
	    data: {
	        authorities: [],
	        pageTitle: 'home.title'
	    }
	};
	
	export const ABOUT_ROUTE: Route = {
	    path: 'about',
	    component: AboutComponent,
	    data: {
	        authorities: [],
	        pageTitle: 'global.menu.about'
	    }
	};


And finally, into the /src/main/webapp/app/home/home.module.ts


	import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
	import { RouterModule } from '@angular/router';
	
	import { JhipsterPress08SharedModule } from 'app/shared';
	import { HOME_ROUTE, HomeComponent, ABOUT_ROUTE } from './';
	import { AboutComponent } from '../static/about/about.component';
	
	@NgModule({
	    imports: [
	              JhipsterPress08SharedModule,
	              RouterModule.forChild([HOME_ROUTE, ABOUT_ROUTE])
	              ],
	    declarations: [HomeComponent, AboutComponent],
	    schemas: [CUSTOM_ELEMENTS_SCHEMA]
	})
	export class JhipsterPress08HomeModule {}
	
PD: You can see other example with the privacy, terms, help, 404, etc...

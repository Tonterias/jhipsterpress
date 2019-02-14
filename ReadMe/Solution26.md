# Problem 26: How to add a new font-awesome icon


First, go to https://fontawesome.com/icons?d=gallery&q=message&s=solid and choose the one you need. In this case, we are going to add comment and envelope to the navbar.

Then, go and add them to the /src/main/webapp/app/vendor.ts file like this: faEnvelope & faComment

	/* after changing this file run 'npm run webpack:build' */
	/* tslint:disable */
	import '../content/css/vendor.css';
	
	// Imports all fontawesome core and solid icons
	
	import { library } from '@fortawesome/fontawesome-svg-core';
	import {
	    faUser,
	    faSort,
	    faEnvelope,
	    faComment,
	    faSortUp,
	    faSortDown,
	    faSync,
	    faEye,
	    faBan,
	    faTimes,
	    faArrowLeft,
	    faSave,
	    faPlus,
	    faPencilAlt,
	    faBars,
	    faThList,
	    faUserPlus,
	    faRoad,
	    faTachometerAlt,
	    faHeart,
	    faList,
	    faBell,
	    faBook,
	    faHdd,
	    faFlag,
	    faWrench,
	    faClock,
	    faCloud,
	    faSignOutAlt,
	    faSignInAlt,
	    faCalendarAlt,
	    faSearch,
	    faTrashAlt,
	    faAsterisk,
	    faTasks,
	    faHome
	} from '@fortawesome/free-solid-svg-icons';
	
	// Adds the SVG icon to the library so you can use it in your page
	library.add(faUser);
	library.add(faSort);
	library.add(faEnvelope);
	library.add(faComment);
	library.add(faSortUp);
	library.add(faSortDown);
	library.add(faSync);
	library.add(faEye);
	library.add(faBan);
	library.add(faTimes);
	library.add(faArrowLeft);
	library.add(faSave);
	library.add(faPlus);
	library.add(faPencilAlt);
	library.add(faBars);
	library.add(faHome);
	library.add(faThList);
	library.add(faUserPlus);
	library.add(faRoad);
	library.add(faTachometerAlt);
	library.add(faHeart);
	library.add(faList);
	library.add(faBell);
	library.add(faTasks);
	library.add(faBook);
	library.add(faHdd);
	library.add(faFlag);
	library.add(faWrench);
	library.add(faClock);
	library.add(faCloud);
	library.add(faSignOutAlt);
	library.add(faSignInAlt);
	library.add(faCalendarAlt);
	library.add(faSearch);
	library.add(faTrashAlt);
	library.add(faAsterisk);
	
	// jhipster-needle-add-element-to-vendor - JHipster will add new menu items here
	 

We can now use them in our /src/main/webapp/app/layouts/navbar/navbar.component.html 

            <li *ngSwitchCase="true"ngbDropdown class="nav-item dropdown pointer">
               	<a [routerLink]="['/notification']">
					<fa-icon [icon]="'envelope'"></fa-icon>
					<span class="u-label g-font-size-11 g-bg-gray-dark-v2 g-rounded-20 g-px-10">{{numberOfNotifications}}</span>
				</a>
				<a [routerLink]="['/message']">
					<fa-icon [icon]="'comment'"></fa-icon>
					<span class="u-label g-font-size-11 g-bg-gray-dark-v2 g-rounded-20 g-px-10">{{numberOfMessages}}</span>
				</a>
            </li>


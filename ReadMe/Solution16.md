# Problem 16: How to show/hide information in the frontend depending on the user Role

You can do that using the *jhiHasAnyAuthority like in these examples:

	<div *jhiHasAnyAuthority="'ROLE_ADMIN'">This should appear.... when ADMIN is logged </div>
	
	<div *jhiHasAnyAuthority="['ROLE_ADMIN', 'ROLE_USER']">This should appear.... when ADMIN or USER is logged </div>
	


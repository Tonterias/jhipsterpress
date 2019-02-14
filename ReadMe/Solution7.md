# Problem 7: Translations: NOTE: Beware of UTF-8! 

You can translate:

1) Text that appears in an Entity as the name of the Entity. Let's use the User Management entity.

http://localhost:9000/#/user-management 

Locate the part you want to translate (Usuarios): userManagement.home.title and find it in the JSON file of the language you want to change. User.management in Spanish is located here: ../src/main/webapp/i18n/es/user-management.json 

<span jhiTranslate="userManagement.home.title">Users</span> 

<th jhiSortBy="langKey"> <span jhiTranslate="userManagement.langKey">Lang Key</span> <span class="fa fa-sort"></span></th>

../src/main/webapp/i18n/es/user-management.json

	{
	    "userManagement": {
	        "home": {
	            "title": "Usuarios",
	            "createLabel": "Crear un nuevo usuario",
	            "createOrEditLabel": "Crear o editar un usuario"
	        },
	        "created": "A new user is created with identifier {{ param }}",
	        "updated": "An user is updated with identifier {{ param }}",
	        "deleted": "An user is deleted with identifier {{ param }}",
	        "delete": {
	            "question": "Â¿Seguro que quieres eliminar el usuario {{ login }}?"
	        },
	        "detail": {
	            "title": "Usuario"
	        },
	        "login": "Login",
	        "firstName": "Nombre",
	        "lastName": "Apellidos",
	        "email": "Email",
	        "activated": "Activado",
	        "deactivated": "Desactivado",
	        "profiles": "Perfiles",
	        "langKey": "Idioma",
	        "createdBy": "Creado por",
	        "createdDate": "Fecha de creaciÃ³n",
	        "lastModifiedBy": "Modificado por",
	        "lastModifiedDate": "Fecha de modificaciÃ³n"
	    }
	}

../src/main/webapp/i18n/en/user-management.json

	{
	    "userManagement": {
	        "home": {
	            "title": "Users",
	            "createLabel": "Create a new user",
	            "createOrEditLabel": "Create or edit a user"
	        },
	        "created": "A new user is created with identifier {{ param }}",
	        "updated": "An user is updated with identifier {{ param }}",
	        "deleted": "An user is deleted with identifier {{ param }}",
	        "delete": {
	            "question": "Are you sure you want to delete user {{ login }}?"
	        },
	        "detail": {
	            "title": "User"
	        },
	        "login": "Login",
	        "firstName": "First name",
	        "lastName": "Last name",
	        "email": "Email",
	        "activated": "Activated",
	        "deactivated": "Deactivated",
	        "profiles": "Profiles",
	        "langKey": "Language",
	        "createdBy": "Created by",
	        "createdDate": "Created date",
	        "lastModifiedBy": "Modified by",
	        "lastModifiedDate": "Modified date"
	    }
	}

NOTE: if you see weird characters like this: "createdDate": "Fecha de creaciÃ³n", for accents you will need to change your project to UTF-8. Check: https://stackoverflow.com/a/5928310/5724223 


2) Enums from the database: ie: [MALE, FEMALE]. Let's imagine a Gender Enum for both English(en) and Spanish (es)

.../src/main/webapp/i18n/en/gender.json

	<td jhiTranslate="{{'skeletonmemh2App.Gender.' + profile.gender}}">{{profile.gender}}</td>
	
	{
	    "skeletonmemh2App": {
	        "Gender" : {
	            "null": "",
	            "MALE": "Male",
	            "FEMALE": "Female",
	            "OTHER": "Other"
	        }
	    }
	}

.../src/main/webapp/i18n/es/gender.json

	{
	    "jHipsterPress2App": {
	        "Gender" : {
	            "null": "",
	            "MALE": "Hombre",
	            "FEMALE": "Mujer",
	            "OTHER": "Otro"
	        }
	    }
	}


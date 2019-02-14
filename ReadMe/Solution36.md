# Problem 36: * How to get dates automatically inserted in your forms:


Most of the Entities in the JHipsterPressp have Creation Dates in their entity-update.component.ts forms. How can you make the system to automatically load the system date into the input tag:


	<input id="field_creationDate" type="datetime-local" class="form-control" name="creationDate" [(ngModel)]="creationDate" required/>
	
You just need to fill the creationDate variable with the Date-Time variable in the moment format this.creationDate = moment().format(DATE_TIME_FORMAT); and then assign the value to the entity: this.album.creationDate = moment(this.creationDate);

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ album }) => {
            this.album = album;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.album.creationDate = moment(this.creationDate);
            this.creationDate = this.album.creationDate != null ? this.album.creationDate.format(DATE_TIME_FORMAT) : null;
        });
        console.log('CONSOLOG: M:ngOnInit & O: this.isSaving : ', this.isSaving);
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.myUser();
        });
    }

# Problem 32: How to omit a dropbox when not needed in the forms


NOTE: JhipsterPress is changing its name to Springular, so be aware of it! I apologize for the inconvinience

Sometimes you do not need to have a dropbox in a form, like in the case of the community http://localhost:9000/#/community/new 
where only the user that is logged can create one. Initially, you will have something like this:

    <div class="form-group">
        <label class="form-control-label" jhiTranslate="jhipsterpressApp.community.user" for="field_user">User</label>
        <select class="form-control" id="field_user" name="user" [(ngModel)]="community.userId"  required>
            <option *ngIf="!editForm.value.user" [ngValue]="null" selected></option>
            <option [ngValue]="userOption.id" *ngFor="let userOption of users; trackBy: trackUserById">{{userOption.id}}</option>
        </select>
    </div>

So let's change the select into a label with the information we need:

    <div class="form-group">
        <label class="form-control-label" jhiTranslate="jhipsterpressApp.community.user" for="field_user">User</label>
        <input type="text" class="form-control" id="id" name="id" [(ngModel)]="community.userId" />
    </div>

Then, you will have to add in your .ts file the user Id you need to use: this.community.userId = res.body.id;

            this.principal.identity().then(account => {
            this.currentAccount = account;
            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount.id : ', this.currentAccount.id);
            this.userService.findById(this.currentAccount.id).subscribe(
                (res: HttpResponse<IUser>) => {
                    this.community.userId = res.body.id;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });

There are plenty of examples in the site. Take a look around. For example: VtopicUpdateComponent
        
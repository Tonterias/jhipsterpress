import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlockuser } from 'app/shared/model/blockuser.model';

@Component({
    selector: 'jhi-blockuser-detail',
    templateUrl: './blockuser-detail.component.html'
})
export class BlockuserDetailComponent implements OnInit {
    blockuser: IBlockuser;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ blockuser }) => {
            this.blockuser = blockuser;
        });
    }

    previousState() {
        window.history.back();
    }
}

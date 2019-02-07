import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INewsletter } from 'app/shared/model/newsletter.model';

@Component({
    selector: 'jhi-newsletter-detail',
    templateUrl: './newsletter-detail.component.html'
})
export class NewsletterDetailComponent implements OnInit {
    newsletter: INewsletter;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ newsletter }) => {
            this.newsletter = newsletter;
        });
    }

    previousState() {
        window.history.back();
    }
}

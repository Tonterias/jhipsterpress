import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITopic } from 'app/shared/model/topic.model';

@Component({
    selector: 'jhi-topic-detail',
    templateUrl: './topic-detail.component.html'
})
export class TopicDetailComponent implements OnInit {
    topic: ITopic;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topic }) => {
            this.topic = topic;
            //            console.log('CONSOLOG: M:ngOnInit & O: this.topic : ', this.topic);
        });
    }

    previousState() {
        window.history.back();
    }
}

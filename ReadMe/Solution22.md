# Problem 22: How to create the FrontPage

We needed to modify the Homepage to load all the Post that made the FrontPage as it is defined in /src/main/java/com/jhipsterpress/web/service/dto/CustomFrontpageconfigDTO.java. So we will have to create a new model object to populate those Post, as is: /src/main/webapp/app/shared/model/customfrontpageconfig.model.ts

	import { Moment } from 'moment';
	import { CustomPost } from 'app/shared/model//custompost.model'
	
	export interface ICustomFrontpageconfig {
	    id?: number;
	    creationDate?: Moment;
	    topNews1?: CustomPost;
			...
	}
	
	export class CustomFrontpageconfig implements ICustomFrontpageconfig {
	    constructor(
	        public id?: number,
	        public creationDate?: Moment,
	        public topNews1?: CustomPost,
	        public topNews2?: CustomPost,
				...
	    ) {}
	}

And another one: /src/main/webapp/app/shared/model/custompost.model.ts to fetch the posts with the information about the Blog (where the Post belongs and its ID) and the profile (where the Post belongs).

	import { Moment } from 'moment';
	import { IComment } from 'app/shared/model//comment.model';
	import { ITag } from 'app/shared/model//tag.model';
	import { ITopic } from 'app/shared/model//topic.model';
	
	export interface ICustomPost {
	    id?: number;
	    creationDate?: Moment;
	    publicationDate?: Moment;
	    headline?: string;
	    leadtext?: string;
	    bodytext?: string;
	    quote?: string;
	    conclusion?: string;
	    imageContentType?: string;
	    image?: any;
	    comments?: IComment[];
	    urllink?: any;
	    blogTitle?: string;
	    blog?: any;
	    profile?: any;
	    tags?: ITag[];
	    topics?: ITopic[];
	}
	
	export class CustomPost implements ICustomPost {
	    constructor(
	        public id?: number,
	        public creationDate?: Moment,
	        public publicationDate?: Moment,
	        public headline?: string,
	        public leadtext?: string,
	        public bodytext?: string,
	        public quote?: string,
	        public conclusion?: string,
	        public imageContentType?: string,
	        public image?: any,
	        public comments?: IComment[],
	        public urllink?: any,
	        public blogTitle?: string,
	        public blog?: any,
	        public profile?: any,
	        public tags?: ITag[],
	        public topics?: ITopic[]
	    ) {}
	}


Then, in the home.component.ts we call the /src/main/webapp/app/entities/frontpageconfig/frontpageconfig.service.ts:

        this.frontpageconfigService
        .findIncludingPosts(1)
        .subscribe(
            (res: HttpResponse<ICustomFrontpageconfig>) => this.paginateFrontpageconfigs(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );

Where the frontpageconfig.service.ts will call the web/rest/FrontpageconfigResource.java:


	package com.jhipsterpress.web.web.rest;
	
	import ....
	
	    /**
	     * GET  /frontpageconfigs/:id/posts : get the "id" frontpageconfig, including posts
	     *
	     * @param id the id of the frontpageconfigDTO to retrieve
	     * @return the ResponseEntity with status 200 (OK) and with body the frontpageconfigDTO, or with status 404 (Not Found)
	     */
	    @GetMapping("/frontpageconfigs/{id}/posts")
	    @Timed
	    public ResponseEntity<CustomFrontpageconfigDTO> getFrontpageconfigIncludingPosts(@PathVariable Long id) {
	        log.debug("REST request to get Frontpageconfig : {}", id);
	        Optional<CustomFrontpageconfigDTO> frontpageconfigDTO = frontpageconfigService.findOneIncludingPosts(id);
	        return ResponseUtil.wrapOrNotFound(frontpageconfigDTO);
	    }
	













		
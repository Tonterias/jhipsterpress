# Problem 37: * How search for different items simultaneously using Swagger:


How would you search for a text, let's say Microservices, into the post headline, the post bodytext and the conclusion and show the results of the 3 searches simultaneously? Let's do it and ask for the 3 searches:

    loadAll() {
        if (this.currentSearch) {
            const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
            query['headline.contains'] = this.currentSearch;
            this.postService
                .query(query)
                .subscribe(
                    (res: HttpResponse<IPost[]>) => {
                        this.posts = res.body;
                        const query2 = {
                                page: this.page - 1,
                                size: this.itemsPerPage,
                                sort: this.sort()
                            };
                            query2['bodytext.contains'] = this.currentSearch;
                            this.postService
                            .query(query2)
                            .subscribe(
                                (res2: HttpResponse<IPost[]>) => {
                                    this.posts = this.filterArray(this.posts.concat(res2.body));
                                    const query3 = {
                                            page: this.page - 1,
                                            size: this.itemsPerPage,
                                            sort: this.sort()
                                        };
                                        query3['conclusion.contains'] = this.currentSearch;
                                        this.postService
                                        .query(query3)
                                        .subscribe(
                                            (res3: HttpResponse<IPost[]>) => {
                                                this.posts = this.filterArray(this.posts.concat(res3.body));
                                                },
                                            (res3: HttpErrorResponse) => this.onError(res3.message)
                                        );

                                    },
                                (res2: HttpErrorResponse) => this.onError(res2.message)
                            );
                        },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.postService
        .query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        })
        .subscribe(
            (res: HttpResponse<IPost[]>) => this.paginatePosts(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    
and filter the results to get them together:

    private filterArray(posts) {
        this.arrayAux = [];
        this.arrayIds = [];
        posts.map(x => {
            if (this.arrayIds.length >= 1 && this.arrayIds.includes(x.id) === false) {
                this.arrayAux.push(x);
                this.arrayIds.push(x.id);
            } else if (this.arrayIds.length === 0) {
                this.arrayAux.push(x);
                this.arrayIds.push(x.id);
            }
        });
        console.log('CONSOLOG: M:filterInterests & O: this.follows : ', this.arrayIds, this.arrayAux);
        return this.arrayAux;
    }

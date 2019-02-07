import { Moment } from 'moment';
import { CustomPost } from 'app/shared/model//custompost.model';

export interface ICustomFrontpageconfig {
    id?: number;
    creationDate?: Moment;
    topNews1?: CustomPost;
    topNews2?: CustomPost;
    topNews3?: CustomPost;
    topNews4?: CustomPost;
    topNews5?: CustomPost;
    latestNews1?: CustomPost;
    latestNews2?: CustomPost;
    latestNews3?: CustomPost;
    latestNews4?: CustomPost;
    latestNews5?: CustomPost;
    breakingNews1?: CustomPost;
    recentPosts1?: CustomPost;
    recentPosts2?: CustomPost;
    recentPosts3?: CustomPost;
    recentPosts4?: CustomPost;
    featuredArticles1?: CustomPost;
    featuredArticles2?: CustomPost;
    featuredArticles3?: CustomPost;
    featuredArticles4?: CustomPost;
    featuredArticles5?: CustomPost;
    featuredArticles6?: CustomPost;
    featuredArticles7?: CustomPost;
    featuredArticles8?: CustomPost;
    featuredArticles9?: CustomPost;
    featuredArticles10?: CustomPost;
    popularNews1?: CustomPost;
    popularNews2?: CustomPost;
    popularNews3?: CustomPost;
    popularNews4?: CustomPost;
    popularNews5?: CustomPost;
    popularNews6?: CustomPost;
    popularNews7?: CustomPost;
    popularNews8?: CustomPost;
    weeklyNews1?: CustomPost;
    weeklyNews2?: CustomPost;
    weeklyNews3?: CustomPost;
    weeklyNews4?: CustomPost;
    newsFeeds1?: CustomPost;
    newsFeeds2?: CustomPost;
    newsFeeds3?: CustomPost;
    newsFeeds4?: CustomPost;
    newsFeeds5?: CustomPost;
    newsFeeds6?: CustomPost;
    usefulLinks1?: CustomPost;
    usefulLinks2?: CustomPost;
    usefulLinks3?: CustomPost;
    usefulLinks4?: CustomPost;
    usefulLinks5?: CustomPost;
    usefulLinks6?: CustomPost;
    recentVideos1?: CustomPost;
    recentVideos2?: CustomPost;
    recentVideos3?: CustomPost;
    recentVideos4?: CustomPost;
    recentVideos5?: CustomPost;
    recentVideos6?: CustomPost;
}

export class CustomFrontpageconfig implements ICustomFrontpageconfig {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public topNews1?: CustomPost,
        public topNews2?: CustomPost,
        public topNews3?: CustomPost,
        public topNews4?: CustomPost,
        public topNews5?: CustomPost,
        public latestNews1?: CustomPost,
        public latestNews2?: CustomPost,
        public latestNews3?: CustomPost,
        public latestNews4?: CustomPost,
        public latestNews5?: CustomPost,
        public breakingNews1?: CustomPost,
        public recentPosts1?: CustomPost,
        public recentPosts2?: CustomPost,
        public recentPosts3?: CustomPost,
        public recentPosts4?: CustomPost,
        public featuredArticles1?: CustomPost,
        public featuredArticles2?: CustomPost,
        public featuredArticles3?: CustomPost,
        public featuredArticles4?: CustomPost,
        public featuredArticles5?: CustomPost,
        public featuredArticles6?: CustomPost,
        public featuredArticles7?: CustomPost,
        public featuredArticles8?: CustomPost,
        public featuredArticles9?: CustomPost,
        public featuredArticles10?: CustomPost,
        public popularNews1?: CustomPost,
        public popularNews2?: CustomPost,
        public popularNews3?: CustomPost,
        public popularNews4?: CustomPost,
        public popularNews5?: CustomPost,
        public popularNews6?: CustomPost,
        public popularNews7?: CustomPost,
        public popularNews8?: CustomPost,
        public weeklyNews1?: CustomPost,
        public weeklyNews2?: CustomPost,
        public weeklyNews3?: CustomPost,
        public weeklyNews4?: CustomPost,
        public newsFeeds1?: CustomPost,
        public newsFeeds2?: CustomPost,
        public newsFeeds3?: CustomPost,
        public newsFeeds4?: CustomPost,
        public newsFeeds5?: CustomPost,
        public newsFeeds6?: CustomPost,
        public usefulLinks1?: CustomPost,
        public usefulLinks2?: CustomPost,
        public usefulLinks3?: CustomPost,
        public usefulLinks4?: CustomPost,
        public usefulLinks5?: CustomPost,
        public usefulLinks6?: CustomPost,
        public recentVideos1?: CustomPost,
        public recentVideos2?: CustomPost,
        public recentVideos3?: CustomPost,
        public recentVideos4?: CustomPost,
        public recentVideos5?: CustomPost,
        public recentVideos6?: CustomPost
    ) {}
}

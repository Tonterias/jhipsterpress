import { Moment } from 'moment';

export interface IFrontpageconfig {
    id?: number;
    creationDate?: Moment;
    topNews1?: number;
    topNews2?: number;
    topNews3?: number;
    topNews4?: number;
    topNews5?: number;
    latestNews1?: number;
    latestNews2?: number;
    latestNews3?: number;
    latestNews4?: number;
    latestNews5?: number;
    breakingNews1?: number;
    recentPosts1?: number;
    recentPosts2?: number;
    recentPosts3?: number;
    recentPosts4?: number;
    featuredArticles1?: number;
    featuredArticles2?: number;
    featuredArticles3?: number;
    featuredArticles4?: number;
    featuredArticles5?: number;
    featuredArticles6?: number;
    featuredArticles7?: number;
    featuredArticles8?: number;
    featuredArticles9?: number;
    featuredArticles10?: number;
    popularNews1?: number;
    popularNews2?: number;
    popularNews3?: number;
    popularNews4?: number;
    popularNews5?: number;
    popularNews6?: number;
    popularNews7?: number;
    popularNews8?: number;
    weeklyNews1?: number;
    weeklyNews2?: number;
    weeklyNews3?: number;
    weeklyNews4?: number;
    newsFeeds1?: number;
    newsFeeds2?: number;
    newsFeeds3?: number;
    newsFeeds4?: number;
    newsFeeds5?: number;
    newsFeeds6?: number;
    usefulLinks1?: number;
    usefulLinks2?: number;
    usefulLinks3?: number;
    usefulLinks4?: number;
    usefulLinks5?: number;
    usefulLinks6?: number;
    recentVideos1?: number;
    recentVideos2?: number;
    recentVideos3?: number;
    recentVideos4?: number;
    recentVideos5?: number;
    recentVideos6?: number;
}

export class Frontpageconfig implements IFrontpageconfig {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public topNews1?: number,
        public topNews2?: number,
        public topNews3?: number,
        public topNews4?: number,
        public topNews5?: number,
        public latestNews1?: number,
        public latestNews2?: number,
        public latestNews3?: number,
        public latestNews4?: number,
        public latestNews5?: number,
        public breakingNews1?: number,
        public recentPosts1?: number,
        public recentPosts2?: number,
        public recentPosts3?: number,
        public recentPosts4?: number,
        public featuredArticles1?: number,
        public featuredArticles2?: number,
        public featuredArticles3?: number,
        public featuredArticles4?: number,
        public featuredArticles5?: number,
        public featuredArticles6?: number,
        public featuredArticles7?: number,
        public featuredArticles8?: number,
        public featuredArticles9?: number,
        public featuredArticles10?: number,
        public popularNews1?: number,
        public popularNews2?: number,
        public popularNews3?: number,
        public popularNews4?: number,
        public popularNews5?: number,
        public popularNews6?: number,
        public popularNews7?: number,
        public popularNews8?: number,
        public weeklyNews1?: number,
        public weeklyNews2?: number,
        public weeklyNews3?: number,
        public weeklyNews4?: number,
        public newsFeeds1?: number,
        public newsFeeds2?: number,
        public newsFeeds3?: number,
        public newsFeeds4?: number,
        public newsFeeds5?: number,
        public newsFeeds6?: number,
        public usefulLinks1?: number,
        public usefulLinks2?: number,
        public usefulLinks3?: number,
        public usefulLinks4?: number,
        public usefulLinks5?: number,
        public usefulLinks6?: number,
        public recentVideos1?: number,
        public recentVideos2?: number,
        public recentVideos3?: number,
        public recentVideos4?: number,
        public recentVideos5?: number,
        public recentVideos6?: number
    ) {}
}

import { element, by, ElementFinder } from 'protractor';

export class FrontpageconfigComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-frontpageconfig div table .btn-danger'));
    title = element.all(by.css('jhi-frontpageconfig div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class FrontpageconfigUpdatePage {
    pageTitle = element(by.id('jhi-frontpageconfig-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    topNews1Input = element(by.id('field_topNews1'));
    topNews2Input = element(by.id('field_topNews2'));
    topNews3Input = element(by.id('field_topNews3'));
    topNews4Input = element(by.id('field_topNews4'));
    topNews5Input = element(by.id('field_topNews5'));
    latestNews1Input = element(by.id('field_latestNews1'));
    latestNews2Input = element(by.id('field_latestNews2'));
    latestNews3Input = element(by.id('field_latestNews3'));
    latestNews4Input = element(by.id('field_latestNews4'));
    latestNews5Input = element(by.id('field_latestNews5'));
    breakingNews1Input = element(by.id('field_breakingNews1'));
    recentPosts1Input = element(by.id('field_recentPosts1'));
    recentPosts2Input = element(by.id('field_recentPosts2'));
    recentPosts3Input = element(by.id('field_recentPosts3'));
    recentPosts4Input = element(by.id('field_recentPosts4'));
    featuredArticles1Input = element(by.id('field_featuredArticles1'));
    featuredArticles2Input = element(by.id('field_featuredArticles2'));
    featuredArticles3Input = element(by.id('field_featuredArticles3'));
    featuredArticles4Input = element(by.id('field_featuredArticles4'));
    featuredArticles5Input = element(by.id('field_featuredArticles5'));
    featuredArticles6Input = element(by.id('field_featuredArticles6'));
    featuredArticles7Input = element(by.id('field_featuredArticles7'));
    featuredArticles8Input = element(by.id('field_featuredArticles8'));
    featuredArticles9Input = element(by.id('field_featuredArticles9'));
    featuredArticles10Input = element(by.id('field_featuredArticles10'));
    popularNews1Input = element(by.id('field_popularNews1'));
    popularNews2Input = element(by.id('field_popularNews2'));
    popularNews3Input = element(by.id('field_popularNews3'));
    popularNews4Input = element(by.id('field_popularNews4'));
    popularNews5Input = element(by.id('field_popularNews5'));
    popularNews6Input = element(by.id('field_popularNews6'));
    popularNews7Input = element(by.id('field_popularNews7'));
    popularNews8Input = element(by.id('field_popularNews8'));
    weeklyNews1Input = element(by.id('field_weeklyNews1'));
    weeklyNews2Input = element(by.id('field_weeklyNews2'));
    weeklyNews3Input = element(by.id('field_weeklyNews3'));
    weeklyNews4Input = element(by.id('field_weeklyNews4'));
    newsFeeds1Input = element(by.id('field_newsFeeds1'));
    newsFeeds2Input = element(by.id('field_newsFeeds2'));
    newsFeeds3Input = element(by.id('field_newsFeeds3'));
    newsFeeds4Input = element(by.id('field_newsFeeds4'));
    newsFeeds5Input = element(by.id('field_newsFeeds5'));
    newsFeeds6Input = element(by.id('field_newsFeeds6'));
    usefulLinks1Input = element(by.id('field_usefulLinks1'));
    usefulLinks2Input = element(by.id('field_usefulLinks2'));
    usefulLinks3Input = element(by.id('field_usefulLinks3'));
    usefulLinks4Input = element(by.id('field_usefulLinks4'));
    usefulLinks5Input = element(by.id('field_usefulLinks5'));
    usefulLinks6Input = element(by.id('field_usefulLinks6'));
    recentVideos1Input = element(by.id('field_recentVideos1'));
    recentVideos2Input = element(by.id('field_recentVideos2'));
    recentVideos3Input = element(by.id('field_recentVideos3'));
    recentVideos4Input = element(by.id('field_recentVideos4'));
    recentVideos5Input = element(by.id('field_recentVideos5'));
    recentVideos6Input = element(by.id('field_recentVideos6'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setTopNews1Input(topNews1) {
        await this.topNews1Input.sendKeys(topNews1);
    }

    async getTopNews1Input() {
        return this.topNews1Input.getAttribute('value');
    }

    async setTopNews2Input(topNews2) {
        await this.topNews2Input.sendKeys(topNews2);
    }

    async getTopNews2Input() {
        return this.topNews2Input.getAttribute('value');
    }

    async setTopNews3Input(topNews3) {
        await this.topNews3Input.sendKeys(topNews3);
    }

    async getTopNews3Input() {
        return this.topNews3Input.getAttribute('value');
    }

    async setTopNews4Input(topNews4) {
        await this.topNews4Input.sendKeys(topNews4);
    }

    async getTopNews4Input() {
        return this.topNews4Input.getAttribute('value');
    }

    async setTopNews5Input(topNews5) {
        await this.topNews5Input.sendKeys(topNews5);
    }

    async getTopNews5Input() {
        return this.topNews5Input.getAttribute('value');
    }

    async setLatestNews1Input(latestNews1) {
        await this.latestNews1Input.sendKeys(latestNews1);
    }

    async getLatestNews1Input() {
        return this.latestNews1Input.getAttribute('value');
    }

    async setLatestNews2Input(latestNews2) {
        await this.latestNews2Input.sendKeys(latestNews2);
    }

    async getLatestNews2Input() {
        return this.latestNews2Input.getAttribute('value');
    }

    async setLatestNews3Input(latestNews3) {
        await this.latestNews3Input.sendKeys(latestNews3);
    }

    async getLatestNews3Input() {
        return this.latestNews3Input.getAttribute('value');
    }

    async setLatestNews4Input(latestNews4) {
        await this.latestNews4Input.sendKeys(latestNews4);
    }

    async getLatestNews4Input() {
        return this.latestNews4Input.getAttribute('value');
    }

    async setLatestNews5Input(latestNews5) {
        await this.latestNews5Input.sendKeys(latestNews5);
    }

    async getLatestNews5Input() {
        return this.latestNews5Input.getAttribute('value');
    }

    async setBreakingNews1Input(breakingNews1) {
        await this.breakingNews1Input.sendKeys(breakingNews1);
    }

    async getBreakingNews1Input() {
        return this.breakingNews1Input.getAttribute('value');
    }

    async setRecentPosts1Input(recentPosts1) {
        await this.recentPosts1Input.sendKeys(recentPosts1);
    }

    async getRecentPosts1Input() {
        return this.recentPosts1Input.getAttribute('value');
    }

    async setRecentPosts2Input(recentPosts2) {
        await this.recentPosts2Input.sendKeys(recentPosts2);
    }

    async getRecentPosts2Input() {
        return this.recentPosts2Input.getAttribute('value');
    }

    async setRecentPosts3Input(recentPosts3) {
        await this.recentPosts3Input.sendKeys(recentPosts3);
    }

    async getRecentPosts3Input() {
        return this.recentPosts3Input.getAttribute('value');
    }

    async setRecentPosts4Input(recentPosts4) {
        await this.recentPosts4Input.sendKeys(recentPosts4);
    }

    async getRecentPosts4Input() {
        return this.recentPosts4Input.getAttribute('value');
    }

    async setFeaturedArticles1Input(featuredArticles1) {
        await this.featuredArticles1Input.sendKeys(featuredArticles1);
    }

    async getFeaturedArticles1Input() {
        return this.featuredArticles1Input.getAttribute('value');
    }

    async setFeaturedArticles2Input(featuredArticles2) {
        await this.featuredArticles2Input.sendKeys(featuredArticles2);
    }

    async getFeaturedArticles2Input() {
        return this.featuredArticles2Input.getAttribute('value');
    }

    async setFeaturedArticles3Input(featuredArticles3) {
        await this.featuredArticles3Input.sendKeys(featuredArticles3);
    }

    async getFeaturedArticles3Input() {
        return this.featuredArticles3Input.getAttribute('value');
    }

    async setFeaturedArticles4Input(featuredArticles4) {
        await this.featuredArticles4Input.sendKeys(featuredArticles4);
    }

    async getFeaturedArticles4Input() {
        return this.featuredArticles4Input.getAttribute('value');
    }

    async setFeaturedArticles5Input(featuredArticles5) {
        await this.featuredArticles5Input.sendKeys(featuredArticles5);
    }

    async getFeaturedArticles5Input() {
        return this.featuredArticles5Input.getAttribute('value');
    }

    async setFeaturedArticles6Input(featuredArticles6) {
        await this.featuredArticles6Input.sendKeys(featuredArticles6);
    }

    async getFeaturedArticles6Input() {
        return this.featuredArticles6Input.getAttribute('value');
    }

    async setFeaturedArticles7Input(featuredArticles7) {
        await this.featuredArticles7Input.sendKeys(featuredArticles7);
    }

    async getFeaturedArticles7Input() {
        return this.featuredArticles7Input.getAttribute('value');
    }

    async setFeaturedArticles8Input(featuredArticles8) {
        await this.featuredArticles8Input.sendKeys(featuredArticles8);
    }

    async getFeaturedArticles8Input() {
        return this.featuredArticles8Input.getAttribute('value');
    }

    async setFeaturedArticles9Input(featuredArticles9) {
        await this.featuredArticles9Input.sendKeys(featuredArticles9);
    }

    async getFeaturedArticles9Input() {
        return this.featuredArticles9Input.getAttribute('value');
    }

    async setFeaturedArticles10Input(featuredArticles10) {
        await this.featuredArticles10Input.sendKeys(featuredArticles10);
    }

    async getFeaturedArticles10Input() {
        return this.featuredArticles10Input.getAttribute('value');
    }

    async setPopularNews1Input(popularNews1) {
        await this.popularNews1Input.sendKeys(popularNews1);
    }

    async getPopularNews1Input() {
        return this.popularNews1Input.getAttribute('value');
    }

    async setPopularNews2Input(popularNews2) {
        await this.popularNews2Input.sendKeys(popularNews2);
    }

    async getPopularNews2Input() {
        return this.popularNews2Input.getAttribute('value');
    }

    async setPopularNews3Input(popularNews3) {
        await this.popularNews3Input.sendKeys(popularNews3);
    }

    async getPopularNews3Input() {
        return this.popularNews3Input.getAttribute('value');
    }

    async setPopularNews4Input(popularNews4) {
        await this.popularNews4Input.sendKeys(popularNews4);
    }

    async getPopularNews4Input() {
        return this.popularNews4Input.getAttribute('value');
    }

    async setPopularNews5Input(popularNews5) {
        await this.popularNews5Input.sendKeys(popularNews5);
    }

    async getPopularNews5Input() {
        return this.popularNews5Input.getAttribute('value');
    }

    async setPopularNews6Input(popularNews6) {
        await this.popularNews6Input.sendKeys(popularNews6);
    }

    async getPopularNews6Input() {
        return this.popularNews6Input.getAttribute('value');
    }

    async setPopularNews7Input(popularNews7) {
        await this.popularNews7Input.sendKeys(popularNews7);
    }

    async getPopularNews7Input() {
        return this.popularNews7Input.getAttribute('value');
    }

    async setPopularNews8Input(popularNews8) {
        await this.popularNews8Input.sendKeys(popularNews8);
    }

    async getPopularNews8Input() {
        return this.popularNews8Input.getAttribute('value');
    }

    async setWeeklyNews1Input(weeklyNews1) {
        await this.weeklyNews1Input.sendKeys(weeklyNews1);
    }

    async getWeeklyNews1Input() {
        return this.weeklyNews1Input.getAttribute('value');
    }

    async setWeeklyNews2Input(weeklyNews2) {
        await this.weeklyNews2Input.sendKeys(weeklyNews2);
    }

    async getWeeklyNews2Input() {
        return this.weeklyNews2Input.getAttribute('value');
    }

    async setWeeklyNews3Input(weeklyNews3) {
        await this.weeklyNews3Input.sendKeys(weeklyNews3);
    }

    async getWeeklyNews3Input() {
        return this.weeklyNews3Input.getAttribute('value');
    }

    async setWeeklyNews4Input(weeklyNews4) {
        await this.weeklyNews4Input.sendKeys(weeklyNews4);
    }

    async getWeeklyNews4Input() {
        return this.weeklyNews4Input.getAttribute('value');
    }

    async setNewsFeeds1Input(newsFeeds1) {
        await this.newsFeeds1Input.sendKeys(newsFeeds1);
    }

    async getNewsFeeds1Input() {
        return this.newsFeeds1Input.getAttribute('value');
    }

    async setNewsFeeds2Input(newsFeeds2) {
        await this.newsFeeds2Input.sendKeys(newsFeeds2);
    }

    async getNewsFeeds2Input() {
        return this.newsFeeds2Input.getAttribute('value');
    }

    async setNewsFeeds3Input(newsFeeds3) {
        await this.newsFeeds3Input.sendKeys(newsFeeds3);
    }

    async getNewsFeeds3Input() {
        return this.newsFeeds3Input.getAttribute('value');
    }

    async setNewsFeeds4Input(newsFeeds4) {
        await this.newsFeeds4Input.sendKeys(newsFeeds4);
    }

    async getNewsFeeds4Input() {
        return this.newsFeeds4Input.getAttribute('value');
    }

    async setNewsFeeds5Input(newsFeeds5) {
        await this.newsFeeds5Input.sendKeys(newsFeeds5);
    }

    async getNewsFeeds5Input() {
        return this.newsFeeds5Input.getAttribute('value');
    }

    async setNewsFeeds6Input(newsFeeds6) {
        await this.newsFeeds6Input.sendKeys(newsFeeds6);
    }

    async getNewsFeeds6Input() {
        return this.newsFeeds6Input.getAttribute('value');
    }

    async setUsefulLinks1Input(usefulLinks1) {
        await this.usefulLinks1Input.sendKeys(usefulLinks1);
    }

    async getUsefulLinks1Input() {
        return this.usefulLinks1Input.getAttribute('value');
    }

    async setUsefulLinks2Input(usefulLinks2) {
        await this.usefulLinks2Input.sendKeys(usefulLinks2);
    }

    async getUsefulLinks2Input() {
        return this.usefulLinks2Input.getAttribute('value');
    }

    async setUsefulLinks3Input(usefulLinks3) {
        await this.usefulLinks3Input.sendKeys(usefulLinks3);
    }

    async getUsefulLinks3Input() {
        return this.usefulLinks3Input.getAttribute('value');
    }

    async setUsefulLinks4Input(usefulLinks4) {
        await this.usefulLinks4Input.sendKeys(usefulLinks4);
    }

    async getUsefulLinks4Input() {
        return this.usefulLinks4Input.getAttribute('value');
    }

    async setUsefulLinks5Input(usefulLinks5) {
        await this.usefulLinks5Input.sendKeys(usefulLinks5);
    }

    async getUsefulLinks5Input() {
        return this.usefulLinks5Input.getAttribute('value');
    }

    async setUsefulLinks6Input(usefulLinks6) {
        await this.usefulLinks6Input.sendKeys(usefulLinks6);
    }

    async getUsefulLinks6Input() {
        return this.usefulLinks6Input.getAttribute('value');
    }

    async setRecentVideos1Input(recentVideos1) {
        await this.recentVideos1Input.sendKeys(recentVideos1);
    }

    async getRecentVideos1Input() {
        return this.recentVideos1Input.getAttribute('value');
    }

    async setRecentVideos2Input(recentVideos2) {
        await this.recentVideos2Input.sendKeys(recentVideos2);
    }

    async getRecentVideos2Input() {
        return this.recentVideos2Input.getAttribute('value');
    }

    async setRecentVideos3Input(recentVideos3) {
        await this.recentVideos3Input.sendKeys(recentVideos3);
    }

    async getRecentVideos3Input() {
        return this.recentVideos3Input.getAttribute('value');
    }

    async setRecentVideos4Input(recentVideos4) {
        await this.recentVideos4Input.sendKeys(recentVideos4);
    }

    async getRecentVideos4Input() {
        return this.recentVideos4Input.getAttribute('value');
    }

    async setRecentVideos5Input(recentVideos5) {
        await this.recentVideos5Input.sendKeys(recentVideos5);
    }

    async getRecentVideos5Input() {
        return this.recentVideos5Input.getAttribute('value');
    }

    async setRecentVideos6Input(recentVideos6) {
        await this.recentVideos6Input.sendKeys(recentVideos6);
    }

    async getRecentVideos6Input() {
        return this.recentVideos6Input.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class FrontpageconfigDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-frontpageconfig-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-frontpageconfig'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}

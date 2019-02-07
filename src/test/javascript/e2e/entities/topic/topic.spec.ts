/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TopicComponentsPage, TopicDeleteDialog, TopicUpdatePage } from './topic.page-object';

const expect = chai.expect;

describe('Topic e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let topicUpdatePage: TopicUpdatePage;
    let topicComponentsPage: TopicComponentsPage;
    let topicDeleteDialog: TopicDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Topics', async () => {
        await navBarPage.goToEntity('topic');
        topicComponentsPage = new TopicComponentsPage();
        await browser.wait(ec.visibilityOf(topicComponentsPage.title), 5000);
        expect(await topicComponentsPage.getTitle()).to.eq('jhipsterpressApp.topic.home.title');
    });

    it('should load create Topic page', async () => {
        await topicComponentsPage.clickOnCreateButton();
        topicUpdatePage = new TopicUpdatePage();
        expect(await topicUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.topic.home.createOrEditLabel');
        await topicUpdatePage.cancel();
    });

    it('should create and save Topics', async () => {
        const nbButtonsBeforeCreate = await topicComponentsPage.countDeleteButtons();

        await topicComponentsPage.clickOnCreateButton();
        await promise.all([
            topicUpdatePage.setTopicNameInput('topicName')
            // topicUpdatePage.postSelectLastOption(),
        ]);
        expect(await topicUpdatePage.getTopicNameInput()).to.eq('topicName');
        await topicUpdatePage.save();
        expect(await topicUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await topicComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Topic', async () => {
        const nbButtonsBeforeDelete = await topicComponentsPage.countDeleteButtons();
        await topicComponentsPage.clickOnLastDeleteButton();

        topicDeleteDialog = new TopicDeleteDialog();
        expect(await topicDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.topic.delete.question');
        await topicDeleteDialog.clickOnConfirmButton();

        expect(await topicComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

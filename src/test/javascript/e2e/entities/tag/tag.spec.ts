/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TagComponentsPage, TagDeleteDialog, TagUpdatePage } from './tag.page-object';

const expect = chai.expect;

describe('Tag e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let tagUpdatePage: TagUpdatePage;
    let tagComponentsPage: TagComponentsPage;
    let tagDeleteDialog: TagDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Tags', async () => {
        await navBarPage.goToEntity('tag');
        tagComponentsPage = new TagComponentsPage();
        await browser.wait(ec.visibilityOf(tagComponentsPage.title), 5000);
        expect(await tagComponentsPage.getTitle()).to.eq('jhipsterpressApp.tag.home.title');
    });

    it('should load create Tag page', async () => {
        await tagComponentsPage.clickOnCreateButton();
        tagUpdatePage = new TagUpdatePage();
        expect(await tagUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.tag.home.createOrEditLabel');
        await tagUpdatePage.cancel();
    });

    it('should create and save Tags', async () => {
        const nbButtonsBeforeCreate = await tagComponentsPage.countDeleteButtons();

        await tagComponentsPage.clickOnCreateButton();
        await promise.all([
            tagUpdatePage.setTagNameInput('tagName')
            // tagUpdatePage.postSelectLastOption(),
        ]);
        expect(await tagUpdatePage.getTagNameInput()).to.eq('tagName');
        await tagUpdatePage.save();
        expect(await tagUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await tagComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Tag', async () => {
        const nbButtonsBeforeDelete = await tagComponentsPage.countDeleteButtons();
        await tagComponentsPage.clickOnLastDeleteButton();

        tagDeleteDialog = new TagDeleteDialog();
        expect(await tagDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.tag.delete.question');
        await tagDeleteDialog.clickOnConfirmButton();

        expect(await tagComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UrllinkComponentsPage, UrllinkDeleteDialog, UrllinkUpdatePage } from './urllink.page-object';

const expect = chai.expect;

describe('Urllink e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let urllinkUpdatePage: UrllinkUpdatePage;
    let urllinkComponentsPage: UrllinkComponentsPage;
    let urllinkDeleteDialog: UrllinkDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Urllinks', async () => {
        await navBarPage.goToEntity('urllink');
        urllinkComponentsPage = new UrllinkComponentsPage();
        await browser.wait(ec.visibilityOf(urllinkComponentsPage.title), 5000);
        expect(await urllinkComponentsPage.getTitle()).to.eq('jhipsterpressApp.urllink.home.title');
    });

    it('should load create Urllink page', async () => {
        await urllinkComponentsPage.clickOnCreateButton();
        urllinkUpdatePage = new UrllinkUpdatePage();
        expect(await urllinkUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.urllink.home.createOrEditLabel');
        await urllinkUpdatePage.cancel();
    });

    it('should create and save Urllinks', async () => {
        const nbButtonsBeforeCreate = await urllinkComponentsPage.countDeleteButtons();

        await urllinkComponentsPage.clickOnCreateButton();
        await promise.all([urllinkUpdatePage.setLinkTextInput('linkText'), urllinkUpdatePage.setLinkURLInput('linkURL')]);
        expect(await urllinkUpdatePage.getLinkTextInput()).to.eq('linkText');
        expect(await urllinkUpdatePage.getLinkURLInput()).to.eq('linkURL');
        await urllinkUpdatePage.save();
        expect(await urllinkUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await urllinkComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Urllink', async () => {
        const nbButtonsBeforeDelete = await urllinkComponentsPage.countDeleteButtons();
        await urllinkComponentsPage.clickOnLastDeleteButton();

        urllinkDeleteDialog = new UrllinkDeleteDialog();
        expect(await urllinkDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.urllink.delete.question');
        await urllinkDeleteDialog.clickOnConfirmButton();

        expect(await urllinkComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

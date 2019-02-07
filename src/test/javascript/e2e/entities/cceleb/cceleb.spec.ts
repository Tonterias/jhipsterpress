/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CcelebComponentsPage, CcelebDeleteDialog, CcelebUpdatePage } from './cceleb.page-object';

const expect = chai.expect;

describe('Cceleb e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let ccelebUpdatePage: CcelebUpdatePage;
    let ccelebComponentsPage: CcelebComponentsPage;
    let ccelebDeleteDialog: CcelebDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Ccelebs', async () => {
        await navBarPage.goToEntity('cceleb');
        ccelebComponentsPage = new CcelebComponentsPage();
        await browser.wait(ec.visibilityOf(ccelebComponentsPage.title), 5000);
        expect(await ccelebComponentsPage.getTitle()).to.eq('jhipsterpressApp.cceleb.home.title');
    });

    it('should load create Cceleb page', async () => {
        await ccelebComponentsPage.clickOnCreateButton();
        ccelebUpdatePage = new CcelebUpdatePage();
        expect(await ccelebUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.cceleb.home.createOrEditLabel');
        await ccelebUpdatePage.cancel();
    });

    it('should create and save Ccelebs', async () => {
        const nbButtonsBeforeCreate = await ccelebComponentsPage.countDeleteButtons();

        await ccelebComponentsPage.clickOnCreateButton();
        await promise.all([
            ccelebUpdatePage.setCelebNameInput('celebName')
            // ccelebUpdatePage.communitySelectLastOption(),
        ]);
        expect(await ccelebUpdatePage.getCelebNameInput()).to.eq('celebName');
        await ccelebUpdatePage.save();
        expect(await ccelebUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await ccelebComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Cceleb', async () => {
        const nbButtonsBeforeDelete = await ccelebComponentsPage.countDeleteButtons();
        await ccelebComponentsPage.clickOnLastDeleteButton();

        ccelebDeleteDialog = new CcelebDeleteDialog();
        expect(await ccelebDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.cceleb.delete.question');
        await ccelebDeleteDialog.clickOnConfirmButton();

        expect(await ccelebComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

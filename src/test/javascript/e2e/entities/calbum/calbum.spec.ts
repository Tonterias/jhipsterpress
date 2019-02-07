/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CalbumComponentsPage, CalbumDeleteDialog, CalbumUpdatePage } from './calbum.page-object';

const expect = chai.expect;

describe('Calbum e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let calbumUpdatePage: CalbumUpdatePage;
    let calbumComponentsPage: CalbumComponentsPage;
    /*let calbumDeleteDialog: CalbumDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Calbums', async () => {
        await navBarPage.goToEntity('calbum');
        calbumComponentsPage = new CalbumComponentsPage();
        await browser.wait(ec.visibilityOf(calbumComponentsPage.title), 5000);
        expect(await calbumComponentsPage.getTitle()).to.eq('jhipsterpressApp.calbum.home.title');
    });

    it('should load create Calbum page', async () => {
        await calbumComponentsPage.clickOnCreateButton();
        calbumUpdatePage = new CalbumUpdatePage();
        expect(await calbumUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.calbum.home.createOrEditLabel');
        await calbumUpdatePage.cancel();
    });

    /* it('should create and save Calbums', async () => {
        const nbButtonsBeforeCreate = await calbumComponentsPage.countDeleteButtons();

        await calbumComponentsPage.clickOnCreateButton();
        await promise.all([
            calbumUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            calbumUpdatePage.setTitleInput('title'),
            calbumUpdatePage.communitySelectLastOption(),
        ]);
        expect(await calbumUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await calbumUpdatePage.getTitleInput()).to.eq('title');
        await calbumUpdatePage.save();
        expect(await calbumUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await calbumComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Calbum', async () => {
        const nbButtonsBeforeDelete = await calbumComponentsPage.countDeleteButtons();
        await calbumComponentsPage.clickOnLastDeleteButton();

        calbumDeleteDialog = new CalbumDeleteDialog();
        expect(await calbumDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.calbum.delete.question');
        await calbumDeleteDialog.clickOnConfirmButton();

        expect(await calbumComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

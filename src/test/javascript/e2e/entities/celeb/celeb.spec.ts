/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CelebComponentsPage, CelebDeleteDialog, CelebUpdatePage } from './celeb.page-object';

const expect = chai.expect;

describe('Celeb e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let celebUpdatePage: CelebUpdatePage;
    let celebComponentsPage: CelebComponentsPage;
    let celebDeleteDialog: CelebDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Celebs', async () => {
        await navBarPage.goToEntity('celeb');
        celebComponentsPage = new CelebComponentsPage();
        await browser.wait(ec.visibilityOf(celebComponentsPage.title), 5000);
        expect(await celebComponentsPage.getTitle()).to.eq('jhipsterpressApp.celeb.home.title');
    });

    it('should load create Celeb page', async () => {
        await celebComponentsPage.clickOnCreateButton();
        celebUpdatePage = new CelebUpdatePage();
        expect(await celebUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.celeb.home.createOrEditLabel');
        await celebUpdatePage.cancel();
    });

    it('should create and save Celebs', async () => {
        const nbButtonsBeforeCreate = await celebComponentsPage.countDeleteButtons();

        await celebComponentsPage.clickOnCreateButton();
        await promise.all([
            celebUpdatePage.setCelebNameInput('celebName')
            // celebUpdatePage.uprofileSelectLastOption(),
        ]);
        expect(await celebUpdatePage.getCelebNameInput()).to.eq('celebName');
        await celebUpdatePage.save();
        expect(await celebUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await celebComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Celeb', async () => {
        const nbButtonsBeforeDelete = await celebComponentsPage.countDeleteButtons();
        await celebComponentsPage.clickOnLastDeleteButton();

        celebDeleteDialog = new CelebDeleteDialog();
        expect(await celebDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.celeb.delete.question');
        await celebDeleteDialog.clickOnConfirmButton();

        expect(await celebComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

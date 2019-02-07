/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AlbumComponentsPage, AlbumDeleteDialog, AlbumUpdatePage } from './album.page-object';

const expect = chai.expect;

describe('Album e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let albumUpdatePage: AlbumUpdatePage;
    let albumComponentsPage: AlbumComponentsPage;
    /*let albumDeleteDialog: AlbumDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Albums', async () => {
        await navBarPage.goToEntity('album');
        albumComponentsPage = new AlbumComponentsPage();
        await browser.wait(ec.visibilityOf(albumComponentsPage.title), 5000);
        expect(await albumComponentsPage.getTitle()).to.eq('jhipsterpressApp.album.home.title');
    });

    it('should load create Album page', async () => {
        await albumComponentsPage.clickOnCreateButton();
        albumUpdatePage = new AlbumUpdatePage();
        expect(await albumUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.album.home.createOrEditLabel');
        await albumUpdatePage.cancel();
    });

    /* it('should create and save Albums', async () => {
        const nbButtonsBeforeCreate = await albumComponentsPage.countDeleteButtons();

        await albumComponentsPage.clickOnCreateButton();
        await promise.all([
            albumUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            albumUpdatePage.setTitleInput('title'),
            albumUpdatePage.userSelectLastOption(),
        ]);
        expect(await albumUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await albumUpdatePage.getTitleInput()).to.eq('title');
        await albumUpdatePage.save();
        expect(await albumUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await albumComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Album', async () => {
        const nbButtonsBeforeDelete = await albumComponentsPage.countDeleteButtons();
        await albumComponentsPage.clickOnLastDeleteButton();

        albumDeleteDialog = new AlbumDeleteDialog();
        expect(await albumDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.album.delete.question');
        await albumDeleteDialog.clickOnConfirmButton();

        expect(await albumComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

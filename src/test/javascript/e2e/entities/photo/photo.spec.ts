/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PhotoComponentsPage, PhotoDeleteDialog, PhotoUpdatePage } from './photo.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Photo e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let photoUpdatePage: PhotoUpdatePage;
    let photoComponentsPage: PhotoComponentsPage;
    let photoDeleteDialog: PhotoDeleteDialog;
    const fileNameToUpload = 'logo-jhipster.png';
    const fileToUpload = '../../../../../main/webapp/content/images/' + fileNameToUpload;
    const absolutePath = path.resolve(__dirname, fileToUpload);

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Photos', async () => {
        await navBarPage.goToEntity('photo');
        photoComponentsPage = new PhotoComponentsPage();
        await browser.wait(ec.visibilityOf(photoComponentsPage.title), 5000);
        expect(await photoComponentsPage.getTitle()).to.eq('jhipsterpressApp.photo.home.title');
    });

    it('should load create Photo page', async () => {
        await photoComponentsPage.clickOnCreateButton();
        photoUpdatePage = new PhotoUpdatePage();
        expect(await photoUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.photo.home.createOrEditLabel');
        await photoUpdatePage.cancel();
    });

    it('should create and save Photos', async () => {
        const nbButtonsBeforeCreate = await photoComponentsPage.countDeleteButtons();

        await photoComponentsPage.clickOnCreateButton();
        await promise.all([
            photoUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            photoUpdatePage.setImageInput(absolutePath),
            photoUpdatePage.albumSelectLastOption(),
            photoUpdatePage.calbumSelectLastOption()
        ]);
        expect(await photoUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await photoUpdatePage.getImageInput()).to.endsWith(fileNameToUpload);
        await photoUpdatePage.save();
        expect(await photoUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await photoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Photo', async () => {
        const nbButtonsBeforeDelete = await photoComponentsPage.countDeleteButtons();
        await photoComponentsPage.clickOnLastDeleteButton();

        photoDeleteDialog = new PhotoDeleteDialog();
        expect(await photoDeleteDialog.getDialogTitle()).to.eq('jhipsterpressApp.photo.delete.question');
        await photoDeleteDialog.clickOnConfirmButton();

        expect(await photoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

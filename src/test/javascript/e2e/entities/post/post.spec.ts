/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PostComponentsPage, PostDeleteDialog, PostUpdatePage } from './post.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Post e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let postUpdatePage: PostUpdatePage;
    let postComponentsPage: PostComponentsPage;
    /*let postDeleteDialog: PostDeleteDialog;*/
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

    it('should load Posts', async () => {
        await navBarPage.goToEntity('post');
        postComponentsPage = new PostComponentsPage();
        await browser.wait(ec.visibilityOf(postComponentsPage.title), 5000);
        expect(await postComponentsPage.getTitle()).to.eq('jhipsterpressApp.post.home.title');
    });

    it('should load create Post page', async () => {
        await postComponentsPage.clickOnCreateButton();
        postUpdatePage = new PostUpdatePage();
        expect(await postUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.post.home.createOrEditLabel');
        await postUpdatePage.cancel();
    });

    /* it('should create and save Posts', async () => {
        const nbButtonsBeforeCreate = await postComponentsPage.countDeleteButtons();

        await postComponentsPage.clickOnCreateButton();
        await promise.all([
            postUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            postUpdatePage.setPublicationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            postUpdatePage.setHeadlineInput('headline'),
            postUpdatePage.setLeadtextInput('leadtext'),
            postUpdatePage.setBodytextInput('bodytext'),
            postUpdatePage.setQuoteInput('quote'),
            postUpdatePage.setConclusionInput('conclusion'),
            postUpdatePage.setLinkTextInput('linkText'),
            postUpdatePage.setLinkURLInput('linkURL'),
            postUpdatePage.setImageInput(absolutePath),
            postUpdatePage.userSelectLastOption(),
            postUpdatePage.blogSelectLastOption(),
        ]);
        expect(await postUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await postUpdatePage.getPublicationDateInput()).to.contain('2001-01-01T02:30');
        expect(await postUpdatePage.getHeadlineInput()).to.eq('headline');
        expect(await postUpdatePage.getLeadtextInput()).to.eq('leadtext');
        expect(await postUpdatePage.getBodytextInput()).to.eq('bodytext');
        expect(await postUpdatePage.getQuoteInput()).to.eq('quote');
        expect(await postUpdatePage.getConclusionInput()).to.eq('conclusion');
        expect(await postUpdatePage.getLinkTextInput()).to.eq('linkText');
        expect(await postUpdatePage.getLinkURLInput()).to.eq('linkURL');
        expect(await postUpdatePage.getImageInput()).to.endsWith(fileNameToUpload);
        await postUpdatePage.save();
        expect(await postUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await postComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Post', async () => {
        const nbButtonsBeforeDelete = await postComponentsPage.countDeleteButtons();
        await postComponentsPage.clickOnLastDeleteButton();

        postDeleteDialog = new PostDeleteDialog();
        expect(await postDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.post.delete.question');
        await postDeleteDialog.clickOnConfirmButton();

        expect(await postComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

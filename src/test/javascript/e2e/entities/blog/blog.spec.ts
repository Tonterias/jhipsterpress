/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BlogComponentsPage, BlogDeleteDialog, BlogUpdatePage } from './blog.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Blog e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let blogUpdatePage: BlogUpdatePage;
    let blogComponentsPage: BlogComponentsPage;
    /*let blogDeleteDialog: BlogDeleteDialog;*/
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

    it('should load Blogs', async () => {
        await navBarPage.goToEntity('blog');
        blogComponentsPage = new BlogComponentsPage();
        await browser.wait(ec.visibilityOf(blogComponentsPage.title), 5000);
        expect(await blogComponentsPage.getTitle()).to.eq('jhipsterpressApp.blog.home.title');
    });

    it('should load create Blog page', async () => {
        await blogComponentsPage.clickOnCreateButton();
        blogUpdatePage = new BlogUpdatePage();
        expect(await blogUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.blog.home.createOrEditLabel');
        await blogUpdatePage.cancel();
    });

    /* it('should create and save Blogs', async () => {
        const nbButtonsBeforeCreate = await blogComponentsPage.countDeleteButtons();

        await blogComponentsPage.clickOnCreateButton();
        await promise.all([
            blogUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            blogUpdatePage.setTitleInput('title'),
            blogUpdatePage.setImageInput(absolutePath),
            blogUpdatePage.communitySelectLastOption(),
        ]);
        expect(await blogUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await blogUpdatePage.getTitleInput()).to.eq('title');
        expect(await blogUpdatePage.getImageInput()).to.endsWith(fileNameToUpload);
        await blogUpdatePage.save();
        expect(await blogUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await blogComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Blog', async () => {
        const nbButtonsBeforeDelete = await blogComponentsPage.countDeleteButtons();
        await blogComponentsPage.clickOnLastDeleteButton();

        blogDeleteDialog = new BlogDeleteDialog();
        expect(await blogDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.blog.delete.question');
        await blogDeleteDialog.clickOnConfirmButton();

        expect(await blogComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

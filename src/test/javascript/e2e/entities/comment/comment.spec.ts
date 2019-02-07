/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CommentComponentsPage, CommentDeleteDialog, CommentUpdatePage } from './comment.page-object';

const expect = chai.expect;

describe('Comment e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let commentUpdatePage: CommentUpdatePage;
    let commentComponentsPage: CommentComponentsPage;
    /*let commentDeleteDialog: CommentDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Comments', async () => {
        await navBarPage.goToEntity('comment');
        commentComponentsPage = new CommentComponentsPage();
        await browser.wait(ec.visibilityOf(commentComponentsPage.title), 5000);
        expect(await commentComponentsPage.getTitle()).to.eq('jhipsterpressApp.comment.home.title');
    });

    it('should load create Comment page', async () => {
        await commentComponentsPage.clickOnCreateButton();
        commentUpdatePage = new CommentUpdatePage();
        expect(await commentUpdatePage.getPageTitle()).to.eq('jhipsterpressApp.comment.home.createOrEditLabel');
        await commentUpdatePage.cancel();
    });

    /* it('should create and save Comments', async () => {
        const nbButtonsBeforeCreate = await commentComponentsPage.countDeleteButtons();

        await commentComponentsPage.clickOnCreateButton();
        await promise.all([
            commentUpdatePage.setCreationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            commentUpdatePage.setCommentTextInput('commentText'),
            commentUpdatePage.userSelectLastOption(),
            commentUpdatePage.postSelectLastOption(),
        ]);
        expect(await commentUpdatePage.getCreationDateInput()).to.contain('2001-01-01T02:30');
        expect(await commentUpdatePage.getCommentTextInput()).to.eq('commentText');
        const selectedIsOffensive = commentUpdatePage.getIsOffensiveInput();
        if (await selectedIsOffensive.isSelected()) {
            await commentUpdatePage.getIsOffensiveInput().click();
            expect(await commentUpdatePage.getIsOffensiveInput().isSelected()).to.be.false;
        } else {
            await commentUpdatePage.getIsOffensiveInput().click();
            expect(await commentUpdatePage.getIsOffensiveInput().isSelected()).to.be.true;
        }
        await commentUpdatePage.save();
        expect(await commentUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await commentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last Comment', async () => {
        const nbButtonsBeforeDelete = await commentComponentsPage.countDeleteButtons();
        await commentComponentsPage.clickOnLastDeleteButton();

        commentDeleteDialog = new CommentDeleteDialog();
        expect(await commentDeleteDialog.getDialogTitle())
            .to.eq('jhipsterpressApp.comment.delete.question');
        await commentDeleteDialog.clickOnConfirmButton();

        expect(await commentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

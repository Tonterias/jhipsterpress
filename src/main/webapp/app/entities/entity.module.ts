import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'blog',
                loadChildren: './blog/blog.module#JhipsterpressBlogModule'
            },
            {
                path: 'post',
                loadChildren: './post/post.module#JhipsterpressPostModule'
            },
            {
                path: 'topic',
                loadChildren: './topic/topic.module#JhipsterpressTopicModule'
            },
            {
                path: 'tag',
                loadChildren: './tag/tag.module#JhipsterpressTagModule'
            },
            {
                path: 'comment',
                loadChildren: './comment/comment.module#JhipsterpressCommentModule'
            },
            {
                path: 'cmessage',
                loadChildren: './cmessage/cmessage.module#JhipsterpressCmessageModule'
            },
            {
                path: 'message',
                loadChildren: './message/message.module#JhipsterpressMessageModule'
            },
            {
                path: 'notification',
                loadChildren: './notification/notification.module#JhipsterpressNotificationModule'
            },
            {
                path: 'uprofile',
                loadChildren: './uprofile/uprofile.module#JhipsterpressUprofileModule'
            },
            {
                path: 'community',
                loadChildren: './community/community.module#JhipsterpressCommunityModule'
            },
            {
                path: 'follow',
                loadChildren: './follow/follow.module#JhipsterpressFollowModule'
            },
            {
                path: 'blockuser',
                loadChildren: './blockuser/blockuser.module#JhipsterpressBlockuserModule'
            },
            {
                path: 'album',
                loadChildren: './album/album.module#JhipsterpressAlbumModule'
            },
            {
                path: 'calbum',
                loadChildren: './calbum/calbum.module#JhipsterpressCalbumModule'
            },
            {
                path: 'photo',
                loadChildren: './photo/photo.module#JhipsterpressPhotoModule'
            },
            {
                path: 'interest',
                loadChildren: './interest/interest.module#JhipsterpressInterestModule'
            },
            {
                path: 'activity',
                loadChildren: './activity/activity.module#JhipsterpressActivityModule'
            },
            {
                path: 'celeb',
                loadChildren: './celeb/celeb.module#JhipsterpressCelebModule'
            },
            {
                path: 'cinterest',
                loadChildren: './cinterest/cinterest.module#JhipsterpressCinterestModule'
            },
            {
                path: 'cactivity',
                loadChildren: './cactivity/cactivity.module#JhipsterpressCactivityModule'
            },
            {
                path: 'cceleb',
                loadChildren: './cceleb/cceleb.module#JhipsterpressCcelebModule'
            },
            {
                path: 'urllink',
                loadChildren: './urllink/urllink.module#JhipsterpressUrllinkModule'
            },
            {
                path: 'frontpageconfig',
                loadChildren: './frontpageconfig/frontpageconfig.module#JhipsterpressFrontpageconfigModule'
            },
            {
                path: 'vtopic',
                loadChildren: './vtopic/vtopic.module#JhipsterpressVtopicModule'
            },
            {
                path: 'vquestion',
                loadChildren: './vquestion/vquestion.module#JhipsterpressVquestionModule'
            },
            {
                path: 'vanswer',
                loadChildren: './vanswer/vanswer.module#JhipsterpressVanswerModule'
            },
            {
                path: 'vthumb',
                loadChildren: './vthumb/vthumb.module#JhipsterpressVthumbModule'
            },
            {
                path: 'newsletter',
                loadChildren: './newsletter/newsletter.module#JhipsterpressNewsletterModule'
            },
            {
                path: 'feedback',
                loadChildren: './feedback/feedback.module#JhipsterpressFeedbackModule'
            },
            {
                path: 'config-variables',
                loadChildren: './config-variables/config-variables.module#JhipsterpressConfigVariablesModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressEntityModule {}

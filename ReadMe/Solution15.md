# Problem 15: How to allow HTML code inside a text field

You  may need to let HTML work inside the body of your post with a [innerHTML]="post.bodytext" tag as opposed to the regular tag {{post.quote}} that will not display it. You have an example in /src/main/webapp/app/entities/post/post-detail.component.html

	<p class="g-color-gray-dark-v2" [innerHTML]="post.bodytext"></p>                
		<div class="g-width-70x--md g-my-40">
			<blockquote class="blockquote g-brd-left g-brd-2 g-brd-gray-light-v4 g-brd-primary--hover text-uppercase g-font-size-22 g-transition-0_2 g-pl-20 g-mb-30">
				<p class="g-color-primary">{{post.quote}}</p>
			</blockquote>
		</div>


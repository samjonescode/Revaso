export class Post {
    constructor(private postId: number = 0, private postAuthorId: number=0, private postTitle: string = '', private postBody: string = '',num_likes: number=0, whosLiked:Number[]=[]) {}
}